package com.zvoa.maths.tfl.grammar;

import java.util.*;

public class Application {

    /*
    S -a
S -SS
S -S*
S -S"
S -"S
S -(S)
S -e
     */
//    static String[] productions;
//    static String tempNT = "";
//    static HashMap<String, String[]> hmap = new HashMap<String, String[]>();
//
//    static void initHash() {
//
//        Stack<String> stack[] = new Stack[tempNT.length()];
//        String[] hmapString;
//
//        for(int i = 0; i < stack.length; i++)
//            stack[i] = new Stack<String>();
//
//        for(int i = 0; i < productions.length; i++)
//            stack[tempNT.indexOf(productions[i].charAt(0))].push(productions[i].substring(3));
//
//        for(int i = 0; i < stack.length; i++) {
//
//            hmapString = new String[stack[i].size()];
//
//            if(!stack[i].empty()) {
//
//                for(int j = 0; !stack[i].empty(); j++)
//                    hmapString[j] = stack[i].pop();
//
//                hmap.put((tempNT.charAt(i) + ""), hmapString);
//            }
//        }
//    }
//
//    static void printProductions() {
//
//        for(int i = 0; i < productions.length; i++)
//            System.out.println(productions[i]);
//    }
//
//    static void removeImmediate(String indexName, String[] q) {
//
//        Stack<String> q1 = new Stack<String>();
//        Stack<String> q2 = new Stack<String>();
//
//        for(int i = 0; i < q.length; i++) {
//
//            if (indexName.equals(q[i].charAt(0) + ""))
//                q1.push(q[i]);				//Recursive productions
//
//            else
//                q2.push(q[i]);
//        }
//
//        if(!q1.empty()) {
//
//            hmap.remove(indexName);
//            String[] hmapString = new String[2 * q2.size()];
//
//            int i = 0;
//            while(!q2.empty()) {
//
//                hmapString[i++] = q2.peek();
//                hmapString[i++] = (q2.pop() + "k" + indexName);
//            }
//            hmap.put(indexName, hmapString);
//
//            hmapString = new String[2 * q1.size()];
//            i = 0;
//            while(!q1.empty()) {
//
//                hmapString[i++] = q1.peek().substring(1);
//                hmapString[i++] = (q1.pop().substring(1) + "k" + indexName);
//            }
//            hmap.put(("k" + indexName), hmapString);
//        }
//    }
//
//    static void printHashMap() {
//
//        Set set = hmap.entrySet();
//        Iterator i = set.iterator();
//        String keyName;
//        String keyValues[];
//
//        while(i.hasNext()) {
//
//            Map.Entry me = (Map.Entry)i.next();
//            keyName = me.getKey().toString();
//            keyValues = hmap.get(keyName);
//
//            System.out.print("\n" + keyName + " -> ");
//            for(int j = 0; j < keyValues.length; j++)
//                System.out.print(keyValues[j] + " ");
//        }
//    }
//
//    static void findNonTerminals() {
//
//        for(int i = 0; i < productions.length; i++)
//            for(int j = 0; j < productions[i].length(); j++)
//                if((productions[i].charAt(j) >= 'A' && productions[i].charAt(j) <= 'Z') && (tempNT.indexOf(productions[i].charAt(j)) == -1))
//
//                    tempNT += productions[i].charAt(j);
//
//        System.out.println("\nNon Terminals : " + tempNT);
//    }
//
//    public static void main(String ar[]) {
//
//        Scanner terminal = new Scanner(System.in);
//        System.out.print("Enter the number of productions : ");
//        int noProductions = terminal.nextInt();
//        terminal = new Scanner(System.in);
//        productions = new String[noProductions];
//
//        System.out.println("Enter the productions");
//        for(int i = 0; i < noProductions; i++)
//            productions[i] = terminal.nextLine();
//
//        System.out.println("\nGiven Productions");
//        printProductions();
//        findNonTerminals();
//        initHash();
//        printHashMap();
//
//        for(int i = 0; i < tempNT.length();  i++)
//            if(hmap.containsKey(tempNT.charAt(i) + ""))
//                removeImmediate((tempNT.charAt(i) + ""), hmap.get(tempNT.charAt(i) + ""));
//
//        while(removeIndirect()){}
//
//        for(int i = 0; i < tempNT.length();  i++)
//            if(hmap.containsKey(tempNT.charAt(i) + ""))
//                removeImmediate((tempNT.charAt(i) + ""), hmap.get(tempNT.charAt(i) + ""));
//
//        System.out.print("\n\nAfter removing Left Recursions");
//        printHashMap();
//
//        System.out.println();
//    }
//
//    static boolean removeIndirect() {
//
//        Set set = hmap.entrySet();
//        Iterator i = set.iterator();
//        String keyName;
//        String keyValues[];
//        Stack<String> tempp = new Stack<String>();
//
//        while(i.hasNext()) {
//
//            Map.Entry me = (Map.Entry)i.next();
//            keyName = me.getKey().toString();
//            keyValues = hmap.get(keyName);
//
//            for(int j = 0; j < keyValues.length; j++)
//                if(keyValues[j].charAt(0) >= 'A' && keyValues[j].charAt(0) <= 'Z' && hmap.containsKey(keyValues[j].charAt(0) + ""))
//                    if(tempNT.indexOf(keyName.charAt(0)) > tempNT.indexOf(keyValues[j].charAt(0))) {
//                        String[] sub = hmap.get(keyValues[j].charAt(0) + "");
//                        for(int z = 0; z < keyValues.length; z++)
//                            if(z != j)
//                                tempp.push(keyValues[z]);
//
//                        for(int z = 0; z < sub.length; z++)
//                            tempp.push(sub[z] + keyValues[j].substring(1));
//
//                        String[] hmapString = new String[tempp.size()];
//                        for(int z = 0; !tempp.empty(); z++)
//                            hmapString[z] = tempp.pop();
//
//                        hmap.put(keyName, hmapString);
//                        return true;
//                    }
//        }
//        return false;
//    }
    public static void main(String[] args) {
//        Grammar grammar = GrammarFactory
//                .build()
//                .addNonTerminal("S")
//                .addRule()
//                .addSymbol("A")
//                .addSymbol("b")
//                .endRule()
//                .endNonTerminal()
//                .addNonTerminal("A")
//                .addRule()
//                .addSymbol("S")
//                .addSymbol("a")
//                .endRule()
//                .addRule()
//                .addSymbol("A")
//                .addSymbol("a")
//                .endRule()
//                .endNonTerminal()
//                .endGrammar();
//


//        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
//        System.out.println(grammar);
//        grammar = RemoveUselessSymbols.removeUnreachableSymbols(grammar);
//        System.out.println(grammar);
//        grammar = RemoveUselessSymbols.removeNonGeneratorSymbols(grammar);
//        System.out.println(grammar);
//        grammar = RemoveUselessSymbols.removeChainRules(grammar);
//        System.out.println(grammar);
    }
}
