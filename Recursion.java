package Recursion;

public class Recursion {
    public static void main(String[] args) {
       // System.out.println(factorial(9));
       // System.out.println(SumofNatural(100));
       // System.out.println(Fibonacci(5));
       // int arr[] =  {1,2,3,6,5,3,10,5};
       // System.out.print(isSorted(arr, 0));
       // System.out.print(lastOccurence(arr, 3, 0));
       // System.out.println(optimizedPower(2, 5));
       // System.out.println(tillingProblem(9));
       /* String str = "apnacollege";
       removeDuplicates(str, 0,new StringBuilder("") ,new boolean[26]); */
       //System.out.println(friendsPairing(3));
       printBinStrings(3, 0,new String(""));
    }
    public static int factorial(int n){
        if(n==0){
         return 1;
        }
        return n*factorial(n-1);
    }
    public static int SumofNatural(int n){
        if(n==1){
         return n;
        }
        return n+SumofNatural(n-1);
    }
    public static int Fibonacci(int n){  //returns nth term of fibonacci Series
        if(n<=1){
            return n;
        }
        return Fibonacci(n-1) + Fibonacci(n-2);
    }
    public static void printFibonacci(int n){  //prints first n terms of the series
        for(int i=0;i<n;i++){
            System.out.print(Fibonacci(i)+" ");
        }
    }

    //Check if a given array is sorted or not.
    public static boolean isSorted(int arr[],int i){
        if(i==arr.length-1){
            return true;
        }
        if (arr[i]>arr[i+1]){
            return false;
        }
        return isSorted(arr, i+1);
    }
    public static int firstOccurance(int arr[],int key,int i){
        if(i==arr.length){
            return -1;
        }
        if(arr[i]==key){
            return i;
        }
        else{
            return firstOccurance(arr, key, i+1);
        }
    }
    public static int lastOccurence(int arr[],int key,int i){
        if(i == arr.length){
        return -1;
        }
        int isFound = lastOccurence(arr,key,i+1);
        if(isFound == -1 && arr[i] == key){
        return i;
        }
        return isFound;
    }
    //BASIC APPROACH TO FIND X TO THE POWER N.(TC = O(n))
    /* public static int pow(int x,int n){
        if(n==0){
            return 1; 
        }
        int ans = x*pow(x, n-1);
        return ans;
    } */
    
    //OPTIMIZED APPROACH TO FIND X TO THE POWER N.(TC = O(logn))
    public static int optimizedPower(int x,int n){
        int ans;
        
        if(n==0){
            return 1;
        }
        if(n%2==0){
            int halfPower = optimizedPower(x, n/2);
             ans = halfPower*halfPower;
        }else{
            int halfPower = optimizedPower(x, n/2);
             ans = x*halfPower*halfPower;
            }
             return ans;
    }

    //Tilling Problem
    public static int tillingProblem(int n){ //2 x n (floor size)
        //base case
        if(n==0||n==1){
            return 1;
        }
        //kaam

        //vertical choice
        int fnm1 = tillingProblem(n-1);

        //horizontal choice
        int fnm2 = tillingProblem(n-2);

        int totWays = fnm1 + fnm2;
        return totWays;
    }
    //REMOVE DUPLICATES IN A STRING
    public static void removeDuplicates(String str,int idx,StringBuilder newStr,boolean map[]){
        if(idx == str.length()){
            System.out.println(newStr);
            return;
        }

        //kaam
        char currChar = str.charAt(idx);
        if(map[currChar-'a'] == true){
            //duplicate
            removeDuplicates(str, idx+1, newStr, map);
        } else{
            map[currChar-'a'] = true;
            removeDuplicates(str, idx+1, newStr.append(currChar), map);
        }
    }
    //FRIENDS PAIRING PROBLEM
    public static int friendsPairing(int n){// n is the number of friends
        if(n==1||n==2){
            return n;
        }
        //case 1: Case of single person
        
        int c1 = friendsPairing(n-1);
        //case 2: Case of pairing
        int c2 = (n-1)*friendsPairing(n-2);

        int totalWays = c1 + c2;

        return totalWays;
    }
    //Binary String Problem
    public static void printBinStrings(int n, int lastPlace, String str){
        //base case
        if(n==0){
            System.out.println(str);
            return;
        }
        //kaam 
        if(lastPlace == 0){
            //sit 0 on chair n
            printBinStrings(n-1,0, str+"0");
            printBinStrings(n-1, 1, str+"1");
        } else {
            printBinStrings(n-1, 0, str+"0");
        }
    }
        
}

