package tabela;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Conexao 
{
	static String driver="org.postgresql.Driver";
	private String url,usuario,senha;
	private Connection con;
	Conexao()
	{
		url="jdbc:postgresql://localhost:5432/postgres";
		usuario="postgres";
		senha="123456789";
		try 
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url,usuario,senha);
			System.out.println("Parabéns!Conexão com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int execute(String command)
	{
		try 
		{
			Statement stm=con.createStatement();
			int response= stm.executeUpdate(command);
			stm.close();
			return response;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public void drop(String nome)
	{
		this.execute("drop table if exists "+nome);
		System.out.printf("Tabela dropada %s\n", nome);
	}
	public void createTable(String nome,String args)
	{
		this.drop(nome);
		this.execute("create table "+nome+" ("+args+")");
		System.out.printf("Sucesso! Criação da tabela %s.\n", nome);
	}
	public void insertInto(String nome,String args,String dado)
	{
		String command="INSERT INTO "+ nome + " ("+args+") "+"VALUES ("+dado+")";
		this.execute(command);
		System.out.printf("Sucesso!Inserção: %s\n",dado);
	}
	public void fileInto(String filename,String nome,String args) throws FileNotFoundException
	{
		File arquivo = new File(filename+".txt");
        Scanner sc = new Scanner(arquivo);
		while(sc.hasNextLine())
		{
			String line=sc.nextLine();
			this.insertInto(nome,args,line);
		}
		sc.close();
	}
	public ResultSet busca(String command)
	{
		try {
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(command);
			con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void mostrarBusca(ResultSet rs)
	{
		try 
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			int tam = rsmd.getColumnCount();
			while (rs.next()) 
			{
			    for (int i = 1; i <= tam; i++) 
			    {
			        String dado =rs.getString(i);
			        String nome= rsmd.getColumnName(i);
			        System.out.print(nome + ":" +dado+" ");
			    }
			    System.out.println("");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
