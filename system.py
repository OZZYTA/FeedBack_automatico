import json    

def systemMsgBuild(historias, esquema_salida, archivos, nombre_repo):
    """
    Genera un mensaje de sistema para la evaluación de código mediante OpenAI.

    Esta función construye una instrucción detallada que proporciona contexto al modelo de IA,
    incluyendo la lista de archivos del repositorio, las historias de usuario a evaluar y
    las reglas clave que debe seguir durante el análisis.

    Parámetros:
    - historias (dict): Diccionario que contiene las 18 historias de usuario con sus criterios de evaluación.
    - esquema_salida (dict): Esquema esperado de salida en formato JSON.
    - archivos (dict): Diccionario con la lista de archivos disponibles en el repositorio.
    - nombre_repo (str): Nombre del repositorio en análisis.

    Retorna:
    - str: Un mensaje de sistema estructurado para guiar la evaluación en OpenAI.
    """

    mensaje_sistema = f"""
        Eres un experto en revisión de código para microservicios.
        Tu tarea es analizar el código en el repositorio `{nombre_repo}` y verificar si cada historia de usuario ha sido implementada correctamente.

        **Lista de archivos disponibles en el repositorio:**
        {json.dumps(list(archivos.keys()), ensure_ascii=False, indent=2)}

        **Instrucciones de Evaluación**
        - Evalúa **cada una de las 18 historias de usuario y sus criterios**: {json.dumps(historias, ensure_ascii=False, indent=2)}.
        - **Analiza el código en los archivos disponibles para determinar si cada criterio está implementado.**
        - **Si un criterio se cumple, indica en qué archivo y línea exacta se encuentra.**
        - **Si un criterio no se cumple, explica por qué y qué falta implementar.**
        - **No asumas que algo está implementado si no hay evidencia en el código.**
        - **Se indulgente y trata de encontrar todos los criterios solicitados en las historias en el codigo.**

        **Reglas clave**
        **Solo responde con JSON que corresponda al esquema: {json.dumps(esquema_salida, ensure_ascii=False, indent=2)}.**  
        **Para cada historia, proporciona una calificación indicando cuántos criterios cumple, por ejemplo, `3/5`.**  
        **Si un criterio no se encuentra en el código, no uses "No evaluable", sino que indiques que no se encontró.**  


        **Ejemplo de salida esperada**
        ```json
        {{
        "HU-1": {{
            "Valoracion": "No cumple",
            "Calificacion": "1/4",
            "Retroalimentacion": [
            "Se solicita el campo 'correo', pero no se valida su estructura en UserService.java línea 30.",
            "No se encontró validación de la edad del usuario.",
            "La encriptación de la clave se realiza correctamente en AuthService.java línea 45."
            ]
        }},
        "HU-2": {{
            "Valoracion": "Cumple",
            "Calificacion": "4/4",
            "Retroalimentacion": [
            "El NIT se valida correctamente en RestaurantService.java línea 20.",
            "El nombre del restaurante no permite solo números, validación en RestaurantValidator.java línea 15."
            ]
        }},
        ...
        "HU-18": {{
            "Valoracion": "No cumple",
            "Calificacion": "0/2",
            "Retroalimentacion": [
            "No se encontró la lógica para calcular el tiempo entre inicio y finalización de un pedido.",
            "No se implementa el ranking de eficiencia de empleados."
            ]
        }}
        }}
        ```
    """

    return mensaje_sistema