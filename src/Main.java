import java.sql.PseudoColumnUsage;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void pressAnyKey(){
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue...!");
        input.nextLine();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int floor = 0;
        int room = 0;
        int desireFloor =0;
        int desireRoom =0;
        String ownerCondoName;
        String[][] condo = new String[0][];
        boolean isFloor = false;
        boolean isRroom = false;
        int option = 0;
        System.out.println("================== SET A CONDO =================");
        do{
            System.out.print("Enter number of floor : ");
            floor = input.nextInt();
            if(floor>0){
                isFloor = true;
                do{
                    System.out.print("Enter number of room : ");
                    room = input.nextInt();
                    if(room>0){
                        isRroom = true;
                        condo = new String[floor][room];
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Congratulation...! You succeed of setting a condo...!");
                        System.out.println("Number of floor : "+floor+" floor"+((floor>1)? "s" : ""));
                        System.out.println("Number of room : "+room+ " room"+((room>1)? "s" : ""));
                        System.out.println("Total of room : "+(floor*room)+" room"+((room>1)? "s" : ""));
                    }else {
                        System.out.println("Room cannot be 0 or negative...!");
                    }
                }while (!isRroom);
            }else {
                System.out.println("Floor cannot be 0 or negative...!");
            }
            pressAnyKey();
        }while(!isFloor);
        do {
            System.out.println("================== Welcome to ISTAD Condo =================");
            System.out.println("1. Buy condo ");
            System.out.println("2. Sell condo ");
            System.out.println("3. Search condo information ");
            System.out.println("4. Display information ");
            System.out.println("5. Exit from the program ");
            System.out.println("=============================================================");
            System.out.print("Choose any option : ");
            option = input.nextInt();
            switch (option){
                case 1 -> {
                    String ownerName;
                    boolean isCondoValidInput = false;
                    do{
                        System.out.println("========================= Buying Condo ======================");
                        System.out.print("Enter your desire floor (1 - "+floor+") : ");
                        desireFloor = input.nextInt();
                        if(desireFloor<=floor && desireFloor>0 ){
                            isFloor = true;
                            do{
                                System.out.print("Enter your desire room (1 - "+room+") : ");
                                desireRoom = input.nextInt();
                                if(desireRoom>0 && desireRoom<=room){
                                    if(condo[desireFloor-1][desireRoom-1] == null){
                                        isRroom= true;
                                        isCondoValidInput = true;
                                        System.out.print("Enter owner name : ");
                                        input.nextLine();
                                        ownerName = input.nextLine();
                                        condo[desireFloor - 1][desireRoom - 1] = ownerName;
                                        System.out.println("Congratulation...! You have successfully bought a condo.");
                                    }else {
                                        System.out.println("This condo is already own by someone...!");
                                    }
                                }else {
                                    System.out.println("Invalid room...!");
                                    isRroom = false;
                                }
                            }while (!isRroom);

                        }else {
                            System.out.println("Invalid floor...!");
                            isFloor = false;
                        }
                        pressAnyKey();
                    }while (!isCondoValidInput);
                }
                case 2 -> {
                    int key = 0;
                    do {
                        System.out.println("======================== Sell Condo ==========================");
                        System.out.print("Enter the desire floor for sell : ");
                        desireFloor = input.nextInt();
                        System.out.print("Enter the desire room for sell : ");
                        desireRoom = input.nextInt();
                        if(desireFloor>0 && desireFloor<=floor && desireRoom>0 && desireRoom<=room){
                            first:  for(int i=(condo.length-1); i>=0; i--){
                                for (int j = 0; j < condo[i].length; j++) {
                                    if(condo[desireFloor-1][desireRoom-1]!=null){
                                        if(condo[desireFloor-1][desireRoom-1]==condo[i][j]){
                                            ownerCondoName = condo[i][j];
                                            System.out.println(">>> The owner information <<<");
                                            System.out.println("Floor "+desireFloor+" : Room "+desireRoom+" belong to "+ownerCondoName);
                                            System.out.println("======================================================");
                                            System.out.print("Press 1 to confirm or 0 to cancel : ");
                                            key = input.nextInt();
                                            if(key==1){
                                                condo[desireFloor-1][desireRoom-1]=null;
                                                System.out.println("Congratulation...! You have sold the condo.");
                                                isRroom = true;
                                                break first;

                                            }else {
                                                System.out.println("The plan to sell the condo was cancel...!");
                                                isRroom = true;
                                            }
                                        }
                                    }else {
                                        System.out.println("This condo does not has the owner yet...!");
                                        System.out.print("Press 1 to put again and 0 to out : ");
                                        key = input.nextInt();
                                        // if(key==1){isRroom = false;}else {isRroom = true;} same as isRroom = key !=1;
                                        isRroom = key != 1;
                                        break first;
                                    }
                                }
                            }
                        }else {
                            System.out.println("Error! Floor and room are required...!");
                            System.out.print("Press 1 to put again and 0 to out : ");
                            key = input.nextInt();
                            isRroom = key != 1;
                        }
                    } while (!isRroom);
                }
                case 3 -> {
                    boolean search = false;
                    int searchOption = 0;
                    System.out.println("======================= Search Condo Information ======================");
                    do{
                        System.out.println("1. Search condo by the owner's name ");
                        System.out.println("2. Search condo by floor ");
                        System.out.println("3. Exit ");
                        System.out.print("Choose an option : ");
                        searchOption = input.nextInt();
                        switch (searchOption){
                            case 1 ->{
                                boolean found = false;
                                System.out.println("===================== Search Condo by Owner's Name ===================");
                                System.out.print("Enter the owner name : ");
                                input.nextLine();
                                ownerCondoName = input.nextLine();
                                System.out.println("=======================================================================");
                                first:
                                for(int i=(condo.length-1); i>=0; i--){
                                    second:
                                    for (int j = 0; j < condo[i].length; j++) {
                                        if(condo[i][j]!=null){
                                            if(condo[i][j].equalsIgnoreCase(ownerCondoName)){
                                                found = true;
                                                System.out.println("The owner name | "+ ownerCondoName+ " | in floor ["+(i+1)+"]"+" in room ["+(j+1)+"]");
                                                System.out.println("=======================================================================");
                                                break first;
                                            }else {
                                                System.out.println("User : | "+ownerCondoName+" | does not exist in the system.");
                                                System.out.println("=======================================================================");
                                            }
                                        }
                                    }
                                }
                            }
                            case 2 ->{
                                System.out.println("=================== Search Condo By Floor ====================");
                                int searchFloor = 0;
                                System.out.print("Enter the floor to search : ");
                                searchFloor = input.nextInt();
                                if(searchFloor>0 && searchFloor<=floor){
                                    System.out.print("Floor " + searchFloor + " : ");
                                    for(int i=0; i<condo[searchFloor-1].length;i++){
                                        System.out.print("\t"+condo[searchFloor-1][i]);
                                    }
                                    System.out.println(); //print new line
                                }else {
                                    System.out.println("ERROR! Invalid Floor (Choose from 1 to "+floor+" ) ");
                                }
                            }
                            default -> System.out.println("Exit");
                        }
                        if(searchOption!=3){
                            pressAnyKey();
                        }
                    }while(searchOption!=3);
                }
                case 4 -> {
                    System.out.println("======================= Display Information ======================");
                    for(int i=(condo.length-1); i>=0; i--){
                        System.out.print("Floor ["+(i+1)+"]");
                        for (int j = 0; j < condo[i].length; j++) {
                            System.out.print("\t"+condo[i][j]+" ");
                        }
                        System.out.println();
                    }
                    pressAnyKey();
                }
                default -> System.out.println("Exit");
            }
        }while (option!=5);
    }
}