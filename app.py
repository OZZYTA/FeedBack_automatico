import os
import json
import re
from openai import AzureOpenAI
from dotenv import load_dotenv
from system import systemMsgBuild

# Cargar variables de entorno desde el archivo .env
load_dotenv()

# Configuración del cliente de Azure OpenAI
client = AzureOpenAI(
    api_key=os.getenv("AZURE_OPENAI_API_KEY"),
    api_version=os.getenv("AZURE_OPENAI_API_VERSION"),
    azure_endpoint=os.getenv("AZURE_OPENAI_ENDPOINT")
)

# Definición del modelo de OpenAI a utilizar
DEPLOYMENT_NAME = os.getenv("AZURE_OPENAI_DEPLOYMENT")

# Cargar las historias de usuario y el esquema de salida desde archivos JSON
with open("historias.json", "r", encoding="utf-8") as f:
    historias = json.load(f)

with open("esquema.json", "r", encoding="utf-8") as f:
    esquema_salida = json.load(f)

def leer_archivos_locales(ruta_repo):
    """
    Lee todos los archivos de código dentro de un repositorio local, 
    ignorando archivos binarios u otros formatos irrelevantes.

    Parámetros:
    - ruta_repo (str): Ruta del repositorio local a analizar.

    Retorna:
    - dict: Un diccionario donde las claves son los nombres de los archivos 
      y los valores son sus contenidos en texto.
    """
    print(f"\nLeyendo archivos del repositorio local: {ruta_repo}")

    # Definir extensiones de archivos de código fuente aceptados
    extensiones_validas = {
        ".java", ".py", ".js", ".ts", ".html", ".css", 
        ".xml", ".json", ".yaml", ".yml", ".md"
    }
    
    codigo_fuente = {}

    # Recorrer todos los archivos del repositorio
    for root, _, files in os.walk(ruta_repo):
        for file in files:
            ruta_completa = os.path.join(root, file)
            extension = os.path.splitext(file)[1].lower()

            # Omitir archivos binarios o de formatos no soportados
            if extension not in extensiones_validas:
                print(f"⚠️ Ignorando archivo binario: {ruta_completa}")
                continue

            # Intentar leer el contenido del archivo
            try:
                with open(ruta_completa, "r", encoding="utf-8") as f:
                    contenido = f.read()
                    codigo_fuente[ruta_completa] = contenido
            except Exception as e:
                print(f"⚠️ No se pudo leer {ruta_completa}: {e}")

    print(f"✅ Se encontraron {len(codigo_fuente)} archivos de código en el repositorio local.")
    return codigo_fuente

def evaluar_codigo_local(nombre_repo):
    """
    Evalúa un repositorio local verificando el cumplimiento de historias de usuario 
    mediante el análisis del código fuente con OpenAI.

    Parámetros:
    - nombre_repo (str): Nombre del repositorio dentro de la carpeta 'repos/'.

    Retorna:
    - No retorna un valor, pero guarda los resultados en un archivo JSON.
    """
    ruta_repo = os.path.join("repos", nombre_repo)

    # Verificar si la carpeta del repositorio existe
    if not os.path.exists(ruta_repo):
        print(f"❌ No se encontró la carpeta del repositorio: {ruta_repo}")
        return

    # Leer archivos del repositorio
    archivos = leer_archivos_locales(ruta_repo)

    # Si no hay archivos, detener la evaluación
    if not archivos:
        print("❌ No se encontraron archivos en el repositorio. Deteniendo evaluación.")
        return

    # Construcción del mensaje de sistema usando el generador systemMsgBuild()
    system = systemMsgBuild(historias, esquema_salida, archivos, nombre_repo)

    # Construcción del mensaje de usuario con el código fuente extraído
    contenido_codigos = "\n\n".join([
        f"Archivo: {archivo}\n{contenido}" for archivo, contenido in archivos.items()
    ])

    mensajes = [
        {"role": "system", "content": system},
        {"role": "user", "content": f"Aquí está el código fuente del repositorio `{nombre_repo}`:\n\n{contenido_codigos}"}
    ]

    print("Enviando consulta de evaluación a OpenAI...")

    try:
        # Enviar solicitud al modelo de OpenAI para la evaluación del código
        response = client.chat.completions.create(
            model=DEPLOYMENT_NAME,
            messages=mensajes,
            temperature=0
        )

        # Procesar la respuesta del modelo
        output = response.choices[0].message.content.strip()
        output = re.sub(r"```json|```", "", output).strip()  # Eliminar etiquetas de bloque de código JSON

        # Intentar convertir la respuesta a JSON
        try:
            output_json = json.loads(output)
        except json.JSONDecodeError:
            print("❌ Error: OpenAI devolvió una respuesta inválida.")
            with open("error_output.txt", "w", encoding="utf-8") as f:
                f.write(output)
            return

        # Guardar la evaluación en un archivo JSON con el nombre del repositorio
        nombre_archivo = f"{nombre_repo}.json"

        with open(nombre_archivo, "w", encoding="utf-8") as f:
            json.dump(output_json, f, ensure_ascii=False, indent=2)

        print(f"💾 Evaluación guardada en: {nombre_archivo}")

    except Exception as e:
        print(f"⚠️ Error en la evaluación: {str(e)}")

if __name__ == "__main__":
    """
    Punto de entrada del programa. Solicita al usuario el nombre del repositorio
    local a evaluar y ejecuta el proceso de análisis.
    """
    print("🔹 Bienvenido al Evaluador de Repositorios con OpenAI 🔹\n")
    nombre_repo = input("Ingresa el nombre del repositorio en la carpeta 'repos/': ").strip()

    evaluar_codigo_local(nombre_repo)