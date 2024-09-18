import java.io.File
import kotlin.coroutines.CoroutineContext

class DirGenerator(private val projectDirectory: String = getCurrentProjectDir()) {



    private val topicsAndProblems = mapOf(
        "Array" to listOf(
            "MergeSortedArray", "RemoveElement", "RemoveDuplicatesFromSortedArray",
            "RemoveDuplicatesFromSortedArrayII", "MajorityElement", "RotateArray",
            "BestTimeToBuyAndSellStock", "BestTimeToBuyAndSellStockII", "JumpGame",
            "JumpGameII", "HIndex", "InsertDeleteGetRandomO1", "ProductOfArrayExceptSelf",
            "GasStation", "Candy", "TrappingRainWater", "RomanToInteger", "IntegerToRoman",
            "LengthOfLastWord", "LongestCommonPrefix", "ReverseWordsInAString", "ZigzagConversion",
            "FindTheIndexOfTheFirstOccurrenceInAString", "TextJustification"
        ),


    )

    private val test =  mapOf("TwoPointers" to listOf(
        "ValidPalindrome"
    ))

   suspend fun generate(){


       topicsAndProblems.forEach { (topic, problems) ->
           // Create a directory for the topic

           val topicDir = File("$projectDirectory/src/$topic")
           if (!topicDir.exists()) {
               topicDir.mkdirs()
           }

           problems.forEach { problem ->
               // Create a Kotlin class for each problem
               val problemFile = File(topicDir, "$problem.kt")

               if (!problemFile.exists()) {

                   problemFile.writeText("""
                    // Solution for the $problem problem
                    class $problem {
                        fun solve() {
                            // TODO: Implement the solution
                        }
                    }
                """.trimIndent())
               }

           }
       }

       println("Project structure generated!")
   }
}