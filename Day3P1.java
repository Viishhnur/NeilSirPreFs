/*
 In a futuristic virtual reality (VR) game world, players are provided with custom VR keypads that are similar to 
modern American keyboards but have some restrictions. The keypad is divided into three distinct zones, 
and each zone contains specific letters that the player can use for a task.
	- Zone 1: Letters on the top row: "qwertyuiop"
	- Zone 2: Letters on the middle row: "asdfghjkl"
	- Zone 3: Letters on the bottom row: "zxcvbnm"

Players are given a set of secret codes (words), and they must type these codes using only letters from one zone at
a time. If a word uses letters from more than one zone, it will be considered invalid and cannot be typed.

Your task is to help the players by filtering out the valid secret codes (words) that can be typed using letters 
from only one zone.

Given a list of secret codes (words), return the codes that can be typed using letters from only one zone of the
VR keypad.

Constraints:
------------
1 ≤ secretCodes.length ≤ 20
1 ≤ secretCodes[i].length ≤ 100
secretCodes[i] consists of English letters (both lowercase and uppercase).

Input Format:
-------------
Space separated words, secretCodes[]

Output Format:
--------------
A list of codes that can be typed using letters from only one zone.


Sample Input-1:
---------------
Galaxy Mars Venus Sky

Sample Output-1:
----------------
["Mars", "Sky"]

Explanation:
------------
"Mars" can be typed using only Zone 2.
"Sky" can be typed using only Zone 3.
"Galaxy" and "Venus" use letters from multiple zones and are thus invalid.


Sample Input-2:
---------------
keyboard monkey

Sample Output-2:
----------------
[]

Explanation:
------------
None of the words can be typed using letters from only one zone.


Sample Input-3:
---------------
qw er ty

Sample Output-3:
----------------
["qw", "er", "ty"]

Explanation:
------------
All the codes can be typed using only letters from Zone 1.

 */

 import java.util.*;
// public class Day3P1{
            
//     private static boolean isRestrictedToaZone(String word,HashSet<Character>zone1,HashSet<Character>zone2,HashSet<Character>zone3){
        
//         boolean isZone1Typable = true;
//         for(int i=0;i<word.length();i++){
//             char ch = word.charAt(i);
//             if(!zone1.contains(ch)){
//                 isZone1Typable = false;
//                 break;
//             }
//         }
//         boolean isZone2Typable = true;
//         for(int i=0;i<word.length();i++){
//             char ch = word.charAt(i);
//             if(!zone2.contains(ch)){
//                 isZone2Typable = false;
//                 break;
//             }
//         }
//         boolean isZone3Typable = true;
//         for(int i=0;i<word.length();i++){
//             char ch = word.charAt(i);
//             if(!zone3.contains(ch)){
//                 isZone3Typable = false;
//                 break;
//             }
//         }
        
//         return isZone1Typable || isZone2Typable || isZone3Typable;
//     }
//     private static void fillZone(HashSet<Character> st,String letters){
//         for(int i=0;i<letters.length();i++){
//             st.add(letters.charAt(i));
//         }
//     }
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String[] words = sc.nextLine().split(" ");
        
//         HashSet<Character> zone1 = new HashSet<>();
//         HashSet<Character> zone2 = new HashSet<>();
//         HashSet<Character> zone3 = new HashSet<>();
        
//         fillZone(zone1,"qwertyuiop");
//         fillZone(zone2,"asdfghjkl");
//         fillZone(zone3,"zxcvbnm");
        
//         List<String> al = new ArrayList<>();        
//         for(String word : words){
//             if(isRestrictedToaZone(word,zone1,zone2,zone3)){
//                 al.add(word);
//             }
//         }
        
//         System.out.print(al);
        
//         sc.close();
        
//     }
// }

public class Day3P1{
    private static boolean isRestrictedToZonedWord(String word,int[] zones){
        //    find zone  of first character
        int zone = zones[word.charAt(0) - 'a'];
        for (int i=1 ; i<word.length() ; i++) {
            char ch = word.charAt(i);
            if(zones[ch - 'a'] != zone){
                // if u find any one character whose zone is different return false
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");  
        
        int[] zones = new int[26];

        // fill the zones array with respective zones
        for (char ch : "qwertyuiop".toCharArray()) {
            zones[ch-'a'] = 1; // since these all belong to zone1
        }
        for (char ch : "asdfghjkl".toCharArray()) {
            zones[ch-'a'] = 2; // since these all belong to zone2
        }
        for (char ch : "zxcvbnm".toCharArray()) {
            zones[ch-'a'] = 3; // since these all belong to zone3
        }
        
        List<String> validWords = new ArrayList<>();        
        
        for(String word : words){
            
            if(isRestrictedToZonedWord(word,zones)){
                validWords.add(word);
            }
        }
        
        System.out.print(validWords);
        
        sc.close();
    }
}