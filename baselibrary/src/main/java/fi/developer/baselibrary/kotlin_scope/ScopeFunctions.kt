package fi.developer.baselibrary.kotlin_scope

class ScopeFunctions {
    companion object {
        /**
         * 'let' is used for null-safety and transforming objects.
         * It refers to the object as 'it' and returns the result of the lambda.
         */
        fun getNameByLetScope(name: String?): String? {
            return name?.let {
                // it refers to 'name'
                println("Processing name: $it")
                it.uppercase() // returns uppercase version
            }
        }

        /**
         * 'apply' is used for object configuration.
         * It refers to the object as 'this' and returns the receiver object itself.
         */
        fun getListByApplyScope(): MutableList<Double> {
            return mutableListOf<Double>().apply {
                // this refers to the list
                add(2.71)
                add(3.14)
                add(1.0)
                sort()
            } // returns the list itself
        }

        /**
         * 'also' is used for side effects (like logging) without modifying the flow.
         * It refers to the object as 'it' and returns the receiver object itself.
         */
        fun getListByAlsoScope(): MutableList<Int> {
            return mutableListOf(1, 2, 3).also {
                println("List initialized with: $it")
            } // returns the list itself
        }

        /**
         * 'run' is used for object initialization and computing a result.
         * It refers to the object as 'this' and returns the result of the lambda.
         */
        fun getLengthByRunScope(name: String?): Int {
            return name?.run {
                println("Calculating length for: $this")
                length // returns length
            } ?: 0
        }

        /**
         * 'with' is used to group multiple calls on the same object.
         * It is not an extension function; it takes the object as an argument.
         * Returns the result of the lambda.
         */
        fun getFormattedInfoWithScope(): String {
            val list = mutableListOf("Android", "Kotlin", "MVI")
            return with(list) {
                // this refers to the list
                "The list has $size elements. First: ${first()}"
            }
        }
    }
}