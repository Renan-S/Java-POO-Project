package control;

 
import exception.FornecedorExcecao;
import model.Fornecedor;
import repository.FornecedorRepositorio;
import repository.IFornecedorRepositorio;

public class FornecedorControlador implements IFornecedorControlador {
	private IFornecedorRepositorio 
		repositorio = FornecedorRepositorio.getInstancia();
	
	private static FornecedorControlador instancia;
	
	public static FornecedorControlador getInstancia() {
		if (instancia == null) {
			instancia = new FornecedorControlador();
		}
		return instancia;
	}
	
	private FornecedorControlador() {
	}

	@Override
	public void adicionar(Fornecedor fornecedor) throws FornecedorExcecao {
		if (fornecedor == null) {
			throw new FornecedorExcecao("Fornecedor Inv�lido");
		}
		if (fornecedor.getCnpj() == null) {
			throw new FornecedorExcecao("CNPJ Inv�lido");
		}
		if (repositorio.procurar(fornecedor.getCnpj()) != null){
			throw new FornecedorExcecao("Fornecedor j� existe");
		}
		repositorio.adicionar(fornecedor);	
	}

	@Override
	public Fornecedor procurar(String cnpj) throws FornecedorExcecao {
		
		Fornecedor fornecedor = repositorio.procurar(cnpj);
		
		if (fornecedor == null) {
			throw new FornecedorExcecao("Fornecedor n�o encontrado");
		}
		return fornecedor;
	}

	@Override
	public void remover(String cnpj) throws FornecedorExcecao {
		this.procurar(cnpj);
		repositorio.remover(cnpj);
	}

	@Override
	public void atualizar(Fornecedor fornecedor) throws FornecedorExcecao {
		
		if (fornecedor == null) {
			throw new FornecedorExcecao("Fornecedor Inv�lido");
		}
		if (fornecedor.getCnpj() == null) {
			throw new FornecedorExcecao("CNPJ Inv�lido");
		}
		
		Fornecedor f = this.procurar(fornecedor.getCnpj());
		
		if (!f.getCnpj().equals(fornecedor.getCnpj())){
			throw new FornecedorExcecao("N�o podemos alterar o CNPJ");
		}
		
		repositorio.atulalizar(fornecedor);
	}
	

}

