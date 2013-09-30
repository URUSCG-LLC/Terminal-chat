import java.net.*;
import java.io.*;

public class Client
{
	public static void main(String[] args) throws UnknownHostException
	{
		InetAddress inet = InetAddress.getByName(args[0]);
		int i=Integer.parseInt(args[1]);
		System.out.println("Connecting to host: "+args[0]+" on port: "+i);
		try
		{
			Socket cl=new Socket(inet,i);
			System.out.println("Successfully Connected!");
			while(true)
			{

					DataOutputStream dout = new DataOutputStream(cl.getOutputStream());
					System.out.print("Client: ");
					BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
					String chat=br.readLine();
					if(chat.equals("exit")) break;
					dout.writeUTF(chat);
					DataInputStream din =new DataInputStream(cl.getInputStream());
					System.out.println("Server:"+din.readUTF());
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}