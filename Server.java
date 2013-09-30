import java.net.*;
import java.io.*;

public class Server extends Thread
{
	private ServerSocket ss;

	public Server(int port) throws IOException
	{
		ss= new ServerSocket(port);
		ss.setSoTimeout(100000);
	}

	public void run()
	{
		System.out.println("Waiting for clients at port: "+ss.getLocalPort());
			try
			{
				Socket se= ss.accept();
				while(true)
				{
						DataInputStream din=new DataInputStream(se.getInputStream());
						System.out.println("Client: "+din.readUTF());
						DataOutputStream dout=new DataOutputStream(se.getOutputStream());
						System.out.print("Server: ");
						BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
						String chat=br.readLine();
						if(chat.equals("exit")) break;
						dout.writeUTF(chat);
				}
			}
			catch(SocketTimeoutException s)
			{
				System.out.println("Socket Timed Out!!");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

	}

	public static void main(String[] args)
	{
		try
		{
			Thread t = new Server(Integer.parseInt(args[0]));
			t.start();			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
}