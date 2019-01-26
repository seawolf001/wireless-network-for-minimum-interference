import java.util.*;
import java.lang.*;
import java.io.*;

class extractMatrix
{
        public static void main(String[] args)
        {
                Scanner in = new Scanner(System.in);
                /* basicInfo matrix contains fileId, filename, totalpackets*/
                String[][] basicInfo = getbasicinfo();
                /* macAddressSubstrings hashmap contains substring(0,16) of all the mac_addresses and corresponding fileId */
                HashMap<String,Integer> macAddressSubstrings = getMacAddressSubstringsIntoHashMap();
                /* adjacencyMatrix is the matrix representation of the interference graph */
                float adjacencyMatrix[][] = new float[23][23];
                System.out.println("\nN is the total Number of top interferes you would like to see in the interference Graph");
                System.out.print("Enter value of N : "); int N = in.nextInt();
                System.out.println("----------------------------------------------------------");
                BufferedReader br=null;
                for(int i = 0 ; i < 23 ; i++)
                {
                        int fileId = Integer.parseInt(basicInfo[i][0]);
                        int totalpackets = Integer.parseInt(basicInfo[i][2]);
                        try
                        {
                                String filetobeopened = basicInfo[i][1].concat(".txt");
                                br = new BufferedReader(new FileReader(filetobeopened));
                                System.out.println("Opening file "+filetobeopened+" to read");
                                System.out.println("Number of files opened till now : "+(i+1));
                                System.out.println("Total Packets in this file : "+totalpackets);
                                String line=""; int count=1;
                                System.out.println();
                                while((line=br.readLine())!=null)
                                {
                                        if(count<=N)
                                        {
                                                String arr[] = line.split("\\s+");
                                                int currentPackets=Integer.parseInt(arr[1]);
                                                String bssidsub=arr[2].substring(0,16);
                                                System.out.println(currentPackets+"\t"+bssidsub);
                                                if(macAddressSubstrings.get(bssidsub)!=null)
                                                {
                                                        float result=(float)(currentPackets*100/totalpackets);
                                                        adjacencyMatrix[fileId-1][macAddressSubstrings.get(bssidsub)-1]+=result;
                                                }
                                                count++;
                                        }
                                        else break;
                                }
                                br.close();
                                System.out.println("----------------------------------------------------------");
                        }
                        catch(Exception e)
                        {
                                System.out.println("File not found");
                        }
                }
                /* write the adjacencyMatrix to a textfile which has to be loaded into matlab while plotting the interference graph */
                FileWriter writer=null; BufferedWriter bw=null;
                try
                {
                        writer = new FileWriter("matrix.txt");
                        bw = new BufferedWriter(writer);
                        for(int i = 0 ; i < 23 ; i++)
                        {
                                for(int j = 0 ; j < 23 ; j++)
                                {
                                        bw.write(adjacencyMatrix[i][j]+" ");
                                }
                                bw.newLine();
                        }
                        bw.flush();
                }
                catch(Exception e)
                {
                        System.out.println(e);
                }
        }
        public static String[][] getbasicinfo()
        {
                String[][] basicInfo = new String[23][3];
                try
                {
                        /*totalPacketsInpcap.txt contains fileId, filename, totalpackets */
                        /* here i am reading all this info from file to a 2-dimensional array basicInfo[][] */
                        String aaa="totalPacketsInpcap.txt";
                        File file = new File(aaa);
                        FileReader fileReader = new FileReader(file);
                        BufferedReader br = new BufferedReader(fileReader);
                        String line=""; int counter=0;
                        while((line=br.readLine())!=null)
                        {
                                String arr[] = line.split("\\s+");
                                basicInfo[counter][0]=arr[0];
                                basicInfo[counter][1]=arr[1];
                                basicInfo[counter][2]=arr[2];
                                counter++;
                        }
                        fileReader.close();
                        br.close();
                }
                catch(Exception e)
                {
                        System.out.println("File totalPacketsInpcap.txt was not found!");
                }
                return basicInfo;
        }
        public static HashMap<String,Integer> getMacAddressSubstringsIntoHashMap()
        {
                HashMap<String,Integer> macAddressSubstrings = new HashMap<String,Integer>();
                try
                {
                        /* mac_address.txt contains fileId and all the corresponding mac_addresses of all the APs on the building */
                        /* here I am reading these info and putting substring(0,16) of mac_address and fileId as key-value pair in HashMap*/
                        File file = new File("mac_address.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader br = new BufferedReader(fileReader);
                        String line="";
                        while((line=br.readLine())!=null)
                        {
                                String arr[] = line.split("\\s+");
                                macAddressSubstrings.put(arr[1].substring(0,16),Integer.parseInt(arr[0]));
                        }
                        fileReader.close();
                        br.close();
                }
                catch(Exception e)
                {
                        System.out.println("File mac_address.txt was not found");
                }
                return macAddressSubstrings;
        }
}
