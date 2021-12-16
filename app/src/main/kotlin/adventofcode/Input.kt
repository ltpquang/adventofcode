package adventofcode

class Input {
    companion object {
        fun forDate(year: Int, day: Int): String {
            val testPostfix = System.getProperty("testpostfix", "")
            val res = this::class.java.classLoader.getResource(String.format("y%d/input%02d%s.txt", year, day, testPostfix))
            return res?.readText()?:""
        }
    }
}
