package controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import br.edu.fateczl.Lista.Lista;
import model.Cliente;

public class ModificacaoCadastroController {

	private void novoArquivo(Lista<Cliente> l,String nomeArquivo)throws Exception {
		
		//definindo caminho onde será gravado 
		String caminho= "C:\\TEMP\\"+nomeArquivo;
		
		//usando FileWriter com parametro , se arquivo não existir será criado e possibilita escrever ; se já existir será acrescido de novo texto.
		FileWriter writer =new FileWriter(caminho,true);
		
			//carregando o tamanho da fila 
			int tamanho=l.size();
			//iniciando a string buffer que receberá os dados do cliente
			StringBuffer buffer = new StringBuffer();
			
			//iniciando o contador que percorrerá toda a fila 
			for(int i=0;i<tamanho;i++) {
				//criação de um cliente aux que recebe o cliente da posição do contador
				Cliente aux=l.get(i);			
				//uso do append para escrever , escolhi dar um append por linha para enxergar melhor
				buffer.append(aux.getCpf());
				buffer.append(";");
				buffer.append(aux.getNome());
				buffer.append(";");
				buffer.append(aux.getIdade());
				buffer.append(";");
				buffer.append(aux.getLimCred());
				buffer.append(";");
				//após capturar todos os dados do objeto cliente no append , escrevi no no arquivo
				writer.write(buffer.toString()+"\r\n");//acrescentei a quebra de linha no writer
				buffer.setLength(0);//zerei meu bufferstring para receber novos dados
			}
			writer.close();
		}
	
	public void novoCadastro(int idadeMin,int idadeMax,double limiteCred)throws Exception {
		
		Lista <Cliente> l =new Lista<>();//iniciando lista de clientes
		
		String caminho= "C:\\TEMP\\Cadastro.csv";//caminho do arquivo que será lido
		
		//abertura do bufferreader
		BufferedReader br = new BufferedReader(new FileReader(caminho));//passando a file que está em caminho como parametro
			
			String linha; //variavel que receberá as linhas do arquivo		
			while((linha=br.readLine())!=null) {
				
				String []linhaV= linha.split(";");//fazendo a separação dos dados por ";"
				Cliente cliente = new Cliente(linhaV[0], linhaV[1], linhaV[2], linhaV[3]);//armazenando no objeto cliente				
			
				if(Integer.parseInt(cliente.getIdade())>= idadeMin && Integer.parseInt(cliente.getIdade())<=idadeMax &&
						Double.parseDouble(cliente.getLimCred().trim().replace(",", ".").replaceAll("[^\\d.]",
								"")) > limiteCred){//comparando, uso do trim replace para excluir espaço capturado na String Limite de credito pois estava dando erro
					l.addFirst(cliente);//acrescentando na lista
				}
			}
		
			//chamando método de gravar novo arquivo 
		novoArquivo(l, "Idade " + idadeMin + " - " + idadeMax + " - Limite " + limiteCred + ".csv");
		br.close();
	}
}
