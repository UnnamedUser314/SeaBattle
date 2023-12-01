import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] hiddenBoard = {{1,0,0,0,0,0,0,0,1,0},
                               {1,0,0,0,0,1,1,0,0,0},
                               {1,0,0,0,0,0,0,0,0,0},
                               {1,0,0,1,1,1,0,1,0,0},
                               {0,0,0,0,0,0,0,0,0,0},
                               {0,1,1,1,0,0,1,0,0,0},
                               {0,0,0,0,0,0,1,0,0,1},
                               {1,1,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,0,0,0,0,1},
                               {0,0,0,0,0,0,0,0,0,0}};

        char[][] shownBoard = {{'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'},
                               {'*','*','*','*','*','*','*','*','*','*'}};
        boolean happened = false;
        int[] lonelyPos = new int[2];
        while (true){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    System.out.print(shownBoard[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("Introduce me coordinates of your shot: ");
            int[] shot = new int[2];
            shot[0]=sc.nextInt();
            shot[1]=sc.nextInt();

            if(shotTry(hiddenBoard, shot)==2){
                System.out.println("Ay you killed that bitch man");
                shownBoard[shot[0]][shot[1]]='D';
                hiddenBoard[shot[0]][shot[1]]=0;

                int[] checkingX = isXnear(shownBoard, shot);

                if(checkingX[0]!=-1&&checkingX[1]!=-1){
                    shownBoard[checkingX[0]][checkingX[1]]='D';
                }

                if(stillPlaying(hiddenBoard, shownBoard)==false){
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            System.out.print(shownBoard[i][j]+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Game is finished");
                    break;
                }
            } else if(shotTry(hiddenBoard, shot)==1){
                int[][] positionsToChange=new int[3][2];
                int counter = 0;
                if(happened){
                    positionsToChange[0][0]=lonelyPos[0];
                    positionsToChange[0][1]=lonelyPos[1];
                    counter++;
                }

                happened=false;

                System.out.println("The target is injured, but still alive, finish him: ");
                shownBoard[shot[0]][shot[1]]='X';
                positionsToChange[counter][0]=shot[0];
                positionsToChange[counter][1]=shot[1];
                counter++;
                hiddenBoard[shot[0]][shot[1]]=0;

                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 10; j++){
                        System.out.print(shownBoard[i][j]+" ");
                    }
                    System.out.println();
                }

                shot[0]=sc.nextInt();
                shot[1]=sc.nextInt();

                while (true){
                    if(shotTry(hiddenBoard, shot)==1){
                        System.out.println("The target is injured, but still alive, finish him: ");
                        shownBoard[shot[0]][shot[1]]='X';
                        //(positionsToChange[counter-1][0]+1==shot[0]||positionsToChange[counter-1][0]-1==shot[0]||positionsToChange[counter-1][1]+1==shot[1]||positionsToChange[counter-1][1]-1==shot[1])
                        if(positionsToChange[counter-1][0]+1==shot[0]&&positionsToChange[counter-1][1]==shot[1]){
                            positionsToChange[counter][0]=shot[0];
                            positionsToChange[counter][1]=shot[1];
                            counter++;
                        } else if(positionsToChange[counter-1][0]-1==shot[0]&&positionsToChange[counter-1][1]==shot[1]){
                            positionsToChange[counter][0]=shot[0];
                            positionsToChange[counter][1]=shot[1];
                            counter++;
                        } else if(positionsToChange[counter-1][1]+1==shot[1]&&positionsToChange[counter-1][0]==shot[0]){
                            positionsToChange[counter][0]=shot[0];
                            positionsToChange[counter][1]=shot[1];
                            counter++;
                        } else if(positionsToChange[counter-1][1]-1==shot[1]&&positionsToChange[counter-1][0]==shot[0]){
                            positionsToChange[counter][0]=shot[0];
                            positionsToChange[counter][1]=shot[1];
                            counter++;
                        } else {
                            happened=true;
                            lonelyPos[0]=shot[0];
                            lonelyPos[1]=shot[1];
                            break;
                        }
                        hiddenBoard[shot[0]][shot[1]]=0;

                        for(int i = 0; i < 10; i++){
                            for(int j = 0; j < 10; j++){
                                System.out.print(shownBoard[i][j]+" ");
                            }
                            System.out.println();
                        }

                        shot[0]=sc.nextInt();
                        shot[1]=sc.nextInt();
                    } else if(shotTry(hiddenBoard, shot)==2){
                        System.out.println("Ay you killed that bitch man");
                        shownBoard[shot[0]][shot[1]]='D';
                        hiddenBoard[shot[0]][shot[1]]=0;
                        for(int i = 0; i < counter; i++){
                            shownBoard[positionsToChange[i][0]][positionsToChange[i][1]]='D';
                        }
                        break;
                    } else {
                        System.out.println("You missed that man, try again: ");
                        shownBoard[shot[0]][shot[1]]='N';

                        for(int i = 0; i < 10; i++){
                            for(int j = 0; j < 10; j++){
                                System.out.print(shownBoard[i][j]+" ");
                            }
                            System.out.println();
                        }

                        shot[0]=sc.nextInt();
                        shot[1]=sc.nextInt();
                    }
                }

            } else {
                System.out.println("You missed that man, try again: ");
                shownBoard[shot[0]][shot[1]]='N';
            }
        }
    }
    public static boolean checkClosestPosition(int[] shot, int[][] hiddenBoard){
        boolean hasNeighbor1=false;
        if(shot[0]==0&&shot[1]==0){
            int[][] positionsToCheck = {{shot[0]+1, shot[1]}, {shot[0], shot[1]+1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            }

        } else if(shot[0]==0&&shot[1]==9){
            int[][] positionsToCheck = {{shot[0]+1, shot[1]}, {shot[0], shot[1]-1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            }
        } else if(shot[0]==9&&shot[1]==9){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0], shot[1]-1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            }
        } else if(shot[0]==9&&shot[1]==0){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0], shot[1]+1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            }
        } else if (shot[0]==0){
            int[][] positionsToCheck = {{shot[0], shot[1]-1}, {shot[0], shot[1]+1}, {shot[0]+1, shot[1]}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]==1){
                hasNeighbor1=true;
            }
        } else if (shot[0]==9){
            int[][] positionsToCheck = {{shot[0], shot[1]-1}, {shot[0], shot[1]+1}, {shot[0]-1, shot[1]}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]==1){
                hasNeighbor1=true;
            }
        } else if (shot[1]==0){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]+1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]==1){
                hasNeighbor1=true;
            }
        } else if (shot[1]==9){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]-1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]==1){
                hasNeighbor1=true;
            }
        } else {
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]-1}, {shot[0], shot[1]+1}};
            if(hiddenBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]==1){
                hasNeighbor1=true;
            } else if (hiddenBoard[positionsToCheck[3][0]][positionsToCheck[3][1]]==1){
                hasNeighbor1=true;
            }
        }
        return hasNeighbor1;
    }
    public static int shotTry(int[][] hiddenBoard, int[] shot){
        int toReturn=0;
        if(hiddenBoard[shot[0]][shot[1]]==0){
            toReturn=-1;
        } else if(hiddenBoard[shot[0]][shot[1]]==1&&checkClosestPosition(shot, hiddenBoard)==true){
            toReturn=1;
        } else if(hiddenBoard[shot[0]][shot[1]]==1){
            toReturn=2;
        }
        return toReturn;
    }
    public static boolean stillPlaying(int[][] hiddenBoard, char[][] shownBoard){
        boolean gameContinue = false;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(hiddenBoard[i][j]==1){
                    gameContinue = true;
                }
            }
        }
        if(!gameContinue){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(shownBoard[i][j]=='*'){
                        shownBoard[i][j]='N';
                    }
                }
            }
        }
        return gameContinue;
    }
    public static int[] isXnear(char[][] shownBoard, int[] shot){
        int toReturn[] = new int[2];
        if(shot[0]==0&&shot[1]==0){
            int[][] positionsToCheck = {{shot[0]+1, shot[1]}, {shot[0], shot[1]+1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }

        } else if(shot[0]==0&&shot[1]==9){
            int[][] positionsToCheck = {{shot[0]+1, shot[1]}, {shot[0], shot[1]-1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if(shot[0]==9&&shot[1]==9){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0], shot[1]-1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if(shot[0]==9&&shot[1]==0){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0], shot[1]+1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if (shot[0]==0){
            int[][] positionsToCheck = {{shot[0], shot[1]-1}, {shot[0], shot[1]+1}, {shot[0]+1, shot[1]}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else if (shownBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]=='X'){
                toReturn[0]=positionsToCheck[2][0];
                toReturn[1]=positionsToCheck[2][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if (shot[0]==9){
            int[][] positionsToCheck = {{shot[0], shot[1]-1}, {shot[0], shot[1]+1}, {shot[0]-1, shot[1]}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else if (shownBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]=='X'){
                toReturn[0]=positionsToCheck[2][0];
                toReturn[1]=positionsToCheck[2][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if (shot[1]==0){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]+1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else if (shownBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]=='X'){
                toReturn[0]=positionsToCheck[2][0];
                toReturn[1]=positionsToCheck[2][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else if (shot[1]==9){
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]-1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else if (shownBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]=='X'){
                toReturn[0]=positionsToCheck[2][0];
                toReturn[1]=positionsToCheck[2][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        } else {
            int[][] positionsToCheck = {{shot[0]-1, shot[1]}, {shot[0]+1, shot[1]}, {shot[0], shot[1]-1}, {shot[0], shot[1]+1}};
            if(shownBoard[positionsToCheck[0][0]][positionsToCheck[0][1]]=='X'){
                toReturn[0]=positionsToCheck[0][0];
                toReturn[1]=positionsToCheck[0][1];
            } else if (shownBoard[positionsToCheck[1][0]][positionsToCheck[1][1]]=='X'){
                toReturn[0]=positionsToCheck[1][0];
                toReturn[1]=positionsToCheck[1][1];
            } else if (shownBoard[positionsToCheck[2][0]][positionsToCheck[2][1]]=='X'){
                toReturn[0]=positionsToCheck[2][0];
                toReturn[1]=positionsToCheck[2][1];
            } else if (shownBoard[positionsToCheck[3][0]][positionsToCheck[3][1]]=='X'){
                toReturn[0]=positionsToCheck[3][0];
                toReturn[1]=positionsToCheck[3][1];
            } else {
                toReturn[0]=-1;
                toReturn[1]=-1;
            }
        }
        return toReturn;
    }
}