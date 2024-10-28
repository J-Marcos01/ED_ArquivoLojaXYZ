package model;

public class Cliente {

	private String cpf;
	private String nome ;
	private String idade ;
	private String limCred;
	
	public Cliente(String cpf,String nome,String idade ,String limCred) {
		
		this.cpf=cpf;
		this.idade=idade;
		this.nome=nome;
		this.limCred=limCred;
		
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getIdade() {
		return idade;
	}

	public String getLimCred() {
		return limCred;
	}
	
}
