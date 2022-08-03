// Time Complexity : O(k^(n/k)), k = block length, n = string length
// Space Complexity : O(k^(n/k))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class BraceExpansion {

    class Solution {
        private List<String> result = new ArrayList<>();
        public String[] expand(String s) {
            int len = s.length();
            //create blocks
            List<List<Character>> blocks = new ArrayList<>();
            for(int i=0; i<len; i++) {
                List<Character> block = new ArrayList<Character>();
                if(s.charAt(i) == '{'){
                    i++;
                    while( i<len && s.charAt(i) != '}') {
                        char c = s.charAt(i);
                        if(c != ',')
                            block.add(c);
                        i++;
                    }
                } else {
                    block.add(s.charAt(i));
                }
                blocks.add(block);
            }

            //iterate through the those blocks forming the desired strings in recursive manner
            backtracking(blocks, 0, new StringBuilder());
            String[] output = new String[result.size()];
            for(int j=0; j< result.size(); j++) {
                output[j] = result.get(j);
            }
            Arrays.sort(output);
            return output;
        }

        private void backtracking(List<List<Character>> blocks, int idx, StringBuilder sb) {
            //base
            if(idx == blocks.size()) {
                result.add(sb.toString());
                return;
            }

            //logic
            List<Character> block = blocks.get(idx);
            for(int i=0; i<block.size(); i++) {
                int len = sb.length();
                //main logic
                sb.append(block.get(i));
                //recursive
                backtracking(blocks, idx+1, sb);
                //backtrack to remove last character
                sb.setLength(len);
            }
        }
    }
}
