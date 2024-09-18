import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.CoroutineContext

class DirGenerator(private val projectDirectory: String = getCurrentProjectDir()) {


    val problemsByTopic = mapOf(
        "ArrayString" to listOf(
            "MergeSortedArray", "RemoveElement", "RemoveDuplicatesFromSortedArray",
            "RemoveDuplicatesFromSortedArrayII", "MajorityElement", "RotateArray",
            "BestTimeToBuyAndSellStock", "BestTimeToBuyAndSellStockII",
            "JumpGame", "JumpGameII", "HIndex", "InsertDeleteGetRandomO1",
            "ProductOfArrayExceptSelf", "GasStation", "Candy", "TrappingRainWater",
            "RomanToInteger", "IntegerToRoman", "LengthOfLastWord", "LongestCommonPrefix",
            "ReverseWordsInAString", "ZigzagConversion", "FindTheIndexOfTheFirstOccurrenceInAString",
            "TextJustification"
        ),
        "TwoPointers" to listOf(
            "ValidPalindrome", "IsSubsequence", "TwoSumIIInputArrayIsSorted",
            "ContainerWithMostWater", "ThreeSum"
        ),
        "SlidingWindow" to listOf(
            "MinimumSizeSubarraySum", "LongestSubstringWithoutRepeatingCharacters",
            "SubstringWithConcatenationOfAllWords", "MinimumWindowSubstring"
        ),
        "Matrix" to listOf(
            "ValidSudoku", "SpiralMatrix", "RotateImage", "SetMatrixZeroes", "GameOfLife"
        ),
        "Hashmap" to listOf(
            "RansomNote", "IsomorphicStrings", "WordPattern", "ValidAnagram", "GroupAnagrams",
            "TwoSum", "HappyNumber", "ContainsDuplicateII", "LongestConsecutiveSequence"
        ),
        "Intervals" to listOf(
            "SummaryRanges", "MergeIntervals", "InsertInterval", "MinimumNumberOfArrowsToBurstBalloons"
        ),
        "Stack" to listOf(
            "ValidParentheses", "SimplifyPath", "MinStack", "EvaluateReversePolishNotation", "BasicCalculator"
        ),
        "LinkedList" to listOf(
            "LinkedListCycle", "AddTwoNumbers", "MergeTwoSortedLists", "CopyListWithRandomPointer",
            "ReverseLinkedListII", "ReverseNodesInKGroup", "RemoveNthNodeFromEndOfList",
            "RemoveDuplicatesFromSortedListII", "RotateList", "PartitionList", "LRUCache"
        ),
        "BinaryTreeGeneral" to listOf(
            "MaximumDepthOfBinaryTree", "SameTree", "InvertBinaryTree", "SymmetricTree",
            "ConstructBinaryTreeFromPreorderAndInorderTraversal", "ConstructBinaryTreeFromInorderAndPostorderTraversal",
            "PopulatingNextRightPointersInEachNodeII", "FlattenBinaryTreeToLinkedList", "PathSum",
            "SumRootToLeafNumbers", "BinaryTreeMaximumPathSum", "BinarySearchTreeIterator",
            "CountCompleteTreeNodes", "LowestCommonAncestorOfABinaryTree"
        ),
        "BinaryTreeBFS" to listOf(
            "BinaryTreeRightSideView", "AverageOfLevelsInBinaryTree", "BinaryTreeLevelOrderTraversal",
            "BinaryTreeZigzagLevelOrderTraversal"
        ),
        "BinarySearchTree" to listOf(
            "MinimumAbsoluteDifferenceInBST", "KthSmallestElementInBST", "ValidateBinarySearchTree"
        ),
        "GraphGeneral" to listOf(
            "NumberOfIslands", "SurroundedRegions", "CloneGraph", "EvaluateDivision", "CourseSchedule", "CourseScheduleII"
        ),
        "GraphBFS" to listOf(
            "SnakesAndLadders", "MinimumGeneticMutation", "WordLadder"
        ),
        "Trie" to listOf(
            "ImplementTriePrefixTree", "DesignAddAndSearchWordsDataStructure", "WordSearchII"
        ),
        "Backtracking" to listOf(
            "LetterCombinationsOfAPhoneNumber", "Combinations", "Permutations", "CombinationSum",
            "NQueensII", "GenerateParentheses", "WordSearch"
        ),
        "DivideAndConquer" to listOf(
            "ConvertSortedArrayToBinarySearchTree", "SortList", "ConstructQuadTree", "MergeKSortedLists"
        ),
        "KadanesAlgorithm" to listOf(
            "MaximumSubarray", "MaximumSumCircularSubarray"
        ),
        "BinarySearch" to listOf(
            "SearchInsertPosition", "SearchA2DMatrix", "FindPeakElement", "SearchInRotatedSortedArray",
            "FindFirstAndLastPositionOfElementInSortedArray", "FindMinimumInRotatedSortedArray",
            "MedianOfTwoSortedArrays"
        ),
        "Heap" to listOf(
            "KthLargestElementInAnArray", "IPO", "FindKPairsWithSmallestSums", "FindMedianFromDataStream"
        ),
        "BitManipulation" to listOf(
            "AddBinary", "ReverseBits", "NumberOf1Bits", "SingleNumber", "SingleNumberII", "BitwiseANDOfNumbersRange"
        ),
        "Math" to listOf(
            "PalindromeNumber", "PlusOne", "FactorialTrailingZeroes", "SqrtX", "PowXN", "MaxPointsOnALine"
        ),
        "OneDDP" to listOf(
            "ClimbingStairs", "HouseRobber", "WordBreak", "CoinChange", "LongestIncreasingSubsequence"
        ),
        "MultidimensionalDP" to listOf(
            "Triangle", "MinimumPathSum", "UniquePathsII", "LongestPalindromicSubstring", "InterleavingString", "EditDistance",
            "BestTimeToBuyAndSellStockIII", "BestTimeToBuyAndSellStockIV", "MaximalSquare"
        )
    )

//    val problemsByTopic = mapOf(
//        "Test" to listOf(
//            "SomeFunction"
//        )
//    )


   suspend fun generate(){

       withContext(Dispatchers.IO){
           problemsByTopic.forEach { (topic, problems) ->

               val topicDir =createTopicDir(topic)

               problems.forEach { problem ->

                   val problemFile = File(topicDir, "$problem.kt")

                   if (!problemFile.exists()) {

                     problemFile.writeText("""
                    // Solution for the $problem problem
                    
                    fun ${toCamelCase(problem)}() {
                    // TODO: Implement the solution    
                    }
                    
                    
                """.trimIndent())
                   }

               }
           }
       }


       println("Project structure generated!")
   }

    private  fun createTopicDir(topic: String): File {
        val topicDir = File("$projectDirectory/src/problems/$topic")
        if (!topicDir.exists()) {
            topicDir.mkdirs()
        }
        return topicDir
    }

    private fun toCamelCase(input:String): String {
        val charArray = input.toCharArray()
        charArray[0] = charArray[0].lowercaseChar()
        return charArray.joinToString("");
    }
}
