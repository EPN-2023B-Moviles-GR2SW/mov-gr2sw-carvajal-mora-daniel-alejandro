import java.util.*

fun main(args: Array<String>) {
    println("Hola Mundo!")

    // VARIABLES

    // Inmutables (No Se Reasignan "=")
    val inmutable: String = "Daniel";
    //  inmutable: "Alejandro";

    // Mutables (Re Asignar)
    var mutable: String = "Alejandro";
    mutable = "Daniel";

    // val > var
    // Duck Typing : Entiende los tipos de datos automaticamente
    var ejemploVariable = "Daniel Carvajal"
    val edadEjemplo: Int = 12;
    ejemploVariable.trim() //Le asigno como tipo String

    // VARIABLES PRIMITIVAS
    val nombreProfesor: String = "Adrian Eguez";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    // Importar Clases Java (java.util)
    val fechaNacimiento: Date = Date();

    // SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No Se Sabe")
        }
    }

    // FUNCIONES

    // if - else
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"


    // void = unit
    // Declarar Funciones

    fun imprimirNombre(nombre: String): Unit{
        // "Nombre : " + nombre (otra forma de declarar)
        printl("Nombre : ${nombre}") //Template String
    }


    // Usando la Funcion Calcular Sueldo

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)

    calcularSueldo(10.00, 12.00, 25.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // Named Parameters

}

    // Funcion Completa

    fun calcularSueldo(
        // Declaracion de Variables
        sueldo: Double, // Requerido
        tasa: Double = 12.00, // Opcional (Valores x Defecto)
        bonoEspecial: Double? = null, // Opcion null -> nullable (Puede tener el valor null en algun momento)
    ): Double{
        // Int -> Int? (nullable)
        // String -> String? (nullable)
        // Date -> Date? (nullable)

        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }

    }


    // CLASES

    // Clase Abstracta
    abstract class NumerosJava{
        protected val numeroUno: Int
        private val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ){ // Bloque de codigo del Constructor
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializando")
        }
    }

    // Clase
    abstract class Numeros( // Constructor PRIMARIO
        // Ejemplo:
        // uno: Int, (Parametro (Sin modificador de acceso))
        // private var uno: Int, // Propiedad Publica Clase numeros.uno
        // var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
        // public var uno: Int,
        protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
        protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos
    ){
        // var cedula: string = "" (public es por defecto)
        // private valorCalculado: Int = 0 (private)
        init { // Bloque constructor primario
            this.numeroUno; this.numeroDos; // this es opcional
            numeroUno; numeroDos; //sin el this es lo mismo
            println("Inicializando")
        }

    }

    // Clase

    class Suma( // Constructor Primario Suma
        unoParametro: Int,  // Parametro
        dosParametro: Int,  // Parametro
    ): Numeros(unoParametro, dosParametro){ // Extendiendo y Mandado los Parametros (super)
        init { // Bloque Codigo Constructor Primario
            this.numeroDos
            this.numeroDos
        }

        constructor( // Segundo constructor
            uno: Int?,  // Parametros
            dos: Int    // Parametros
        ):this(
            if(uno == null) 0 else uno,
            dos
        )

        constructor( // Tercer Constructor
            uno: Int,   // Parametros
            dos: Int?   // Parametros
        ):this(
            uno,
            if(dos == null) 0 else dos,
        )
    }
