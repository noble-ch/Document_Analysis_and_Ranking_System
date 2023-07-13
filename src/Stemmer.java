/**
 * 
 * @author Noble
 * 
 * This Program Stems terms in to their root word based on Porter Stemmer algorithm
 * 
 * While this algorithm.saves_memory && increase Processing 
 * but may reduce recall &&  precision 
 * in this code some statements have been commented out in order to balance efficiency.recall
 * 
 * Functionality test Positive
 */
public class Stemmer {
     public String porterStemmer(String word) {

         if (word.length() > 0) {
                 String firstPart = word.substring(0, word.length() - 1);
         }
        char[] wordChars = word.toCharArray();
        int wordLength = wordChars.length;

        if (wordLength > 2) {
            // Step 1a
            if (word.endsWith("sses")) {
                word = word.substring(0, wordLength - 2);
            } else if (word.endsWith("ies")) {
                word = word.substring(0, wordLength - 2);
            } else if (word.endsWith("ss")) {
                word = word;
            } else if (word.endsWith("s")) {
                word = word.substring(0, wordLength - 1);
            }

//            // Step 1b
//            if (wordLength > 3) {
//                if (word.endsWith("eed")) {
//                    if (countConsonants(word, wordLength - 3) > 0) {
//                        word = word.substring(0, wordLength - 1);
//                    }
//                } else if (word.endsWith("ing")) {
//                    if (containsVowel(word, wordLength - 3)) {
//                        word = word.substring(0, wordLength - 3);
//                        if (word.endsWith("at") || word.endsWith("bl") || word.endsWith("iz")) {
//                            word += "e";
//                        } else if (endsWithDoubleConsonant(word)) {
//                            word = word.substring(0, wordLength - 1);
//                        } else if (isShortWord(word)) {
//                            word += "e";
//                        }
//                    }
//                    
//                } else if (word.endsWith("ed")) {
//                    if (containsVowel(word, wordLength - 2)) {
//                        word = word.substring(0, wordLength - 2);
//                        if (word.endsWith("at") || word.endsWith("bl") || word.endsWith("iz")) {
//                            word += "e";
//                        } else if (endsWithDoubleConsonant(word)) {
//                            word = word.substring(0, wordLength - 1);
//                        } else if (isShortWord(word)) {
//                            word += "e";
//                        }
//                    }
//                }
//            }
//
//            // Step 1c
            if (wordLength > 2) {
//                if (word.endsWith("y")) {
//                    if (containsVowel(word, wordLength - 1)) {
//                        word = word.substring(0, wordLength - 1) + "i";
//                    }
//                }
            }
        }
//
//        // Step 2
        if (wordLength > 7) {
//            if (word.endsWith("ational")) {
//                if (countConsonants(word, wordLength - 6) > 0) {
//                    word = word.substring(0, wordLength - 5) + "e";
//                }
//            } else if (word.endsWith("tional")) {
//                if (countConsonants(word, wordLength - 5) > 0) {
//                    word = word.substring(0, wordLength - 2);
//                }
//            }
//        } else if (wordLength > 6) {
//            if (word.endsWith("enci")) {
//                if (countConsonants(word, wordLength - 4) > 0) {
//                    word = word.substring(0, wordLength - 1) + "e";
//                }
//            } else if (word.endsWith("anci")) {
//                if (countConsonants(word, wordLength - 4) > 0) {
//                    word = word.substring(0, wordLength - 1) + "e";
//                }
//            } else if (word.endsWith("izer")) {
//                if (countConsonants(word, wordLength - 4) > 0) {
//                    word = word.substring(0, wordLength - 1);
//                }
//            }
        } else if (wordLength > 5) {
//            if (word.endsWith("abli")) {
//                if (countConsonants(word, wordLength - 4) > 0) {
//                    word = word.substring(0, wordLength - 1) + "e";
//                }
//            } else if (word.endsWith("alli")) {
//                if (countConsonants(word, wordLength - 4) > 0) {
//                    word = word.substring(0, wordLength - 2);
//                }
//            } else if (word.endsWith("entli")) {
//                if (countConsonants(word, wordLength - 5) > 0) {
//                    word = word.substring(0, wordLength - 2);
//                }
//            } else if (word.endsWith("eli")) {
//                if (countConsonants(word, wordLength - 3) > 0) {
//                    word = word.substring(0, wordLength - 2);
//                }
//            } else if (word.endsWith("ousli")) {
//                if (countConsonants(word, wordLength - 5) > 0) {
//                    word = word.substring(0, wordLength - 2);
//                }
//            }
        } else if (wordLength > 4) {
            if (word.endsWith("ization")) {
                if (countConsonants(word, wordLength - 7) > 0) {
                    word = word.substring(0, wordLength - 5);
                }
            } else if (word.endsWith("ation")) {
                if (countConsonants(word, wordLength - 5) > 0) {
                    word = word.substring(0, wordLength - 3);
                }
            } else if (word.endsWith("ator")) {
                if (countConsonants(word, wordLength - 4) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            }
        } else if (wordLength > 3) {
            if (word.endsWith("alism")) {
                if (countConsonants(word, wordLength - 5) > 0) {
                    word = word.substring(0, wordLength - 3);
                }
            } else if (word.endsWith("iveness")) {
                if (countConsonants(word, wordLength - 7) > 0) {
                    word = word.substring(0, wordLength - 4);
                }
            } else if (word.endsWith("fulness")) {
                if (countConsonants(word, wordLength - 7) > 0) {
                    word = word.substring(0, wordLength - 4);
                }
            } else if (word.endsWith("ousness")) {
                if (countConsonants(word, wordLength - 7) > 0) {
                    word = word.substring(0, wordLength - 4);
                }
            }
        } 
                if (wordLength > 2) {
            if (word.endsWith("al")) {
                if (countConsonants(word, wordLength - 2) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            } else if (word.endsWith("ic")) {
                if (countConsonants(word, wordLength - 2) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            } else if (word.endsWith("er")) {
                if (countConsonants(word, wordLength - 2) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            } else if (word.endsWith("ly")) {
                if (countConsonants(word, wordLength - 2) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            } else if (word.endsWith("ed")) {
                if (countConsonants(word, wordLength - 2) > 0) {
                    word = word.substring(0, wordLength - 2);
                }
            } else if (word.endsWith("ing")) {
                if (countConsonants(word, wordLength - 3) > 0) {
                    word = word.substring(0, wordLength - 3);
                }
            }else if (word.endsWith("alism")) {
                if (countConsonants(word, wordLength - 5) > 0) {
                    word = word.substring(0, wordLength - 5);
                }
            }else if (word.endsWith("fulness")) {
                if (countConsonants(word, wordLength - 7) > 0) {
                    word = word.substring(0, wordLength - 4);
                }
            }else if (word.endsWith("izer")) {
                if (countConsonants(word, wordLength - 4) > 0) {
                    word = word.substring(0, wordLength - 1);
                }
            }else if (word.endsWith("tional")) {
                if (countConsonants(word, wordLength - 6) > 0) {
                    word = word.substring(0, wordLength - 6);
                }
            }
        }
                if(wordLength == 1){
                     word = word.substring(0, wordLength - 1);
                }

        return word;
    }

    private boolean containsVowel(String word, int end) {
        char[] wordChars = word.toCharArray();
        for (int i = 0; i <= end; i++) {
            if (isVowel(wordChars[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean endsWithDoubleConsonant(String word) {
        int length = word.length();
        if (length > 1) {
            char[] wordChars = word.toCharArray();
            char lastChar = wordChars[length - 1];
            char secondLastChar = wordChars[length - 2];
            if (isConsonant(lastChar) && isConsonant(secondLastChar)) {
                return lastChar == secondLastChar;
            }
        }
        return false;
    }

    private boolean isShortWord(String word) {
        int length = word.length();
        if (length >= 3) {
            char[] wordChars = word.toCharArray();
            for (int i = 0; i < length; i++) {
                if (isVowel(wordChars[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private boolean isConsonant(char c) {
        return !isVowel(c);
    }

    private int countConsonants(String word, int end) {
        int count = 0;
        char[] wordChars = word.toCharArray();
        for (int i = 0; i <= end; i++) {
            if (isConsonant(wordChars[i])) {
                count++;
            }
        }
        return count;
    }

    
}
