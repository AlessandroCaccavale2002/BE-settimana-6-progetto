package it.business;

import it.data.ContoCorrente;
import it.data.ContoCorrenteDAO;



public class BancomatEjb implements BancomatEjbLocal {
	
	
	private ContoCorrenteDAO ccDAO;

	public BancomatEjb() {
	}

	public static final int TUTTO_OK = 1;
	public static final int QTA_MINORE_0 = -1;
	public static final int  SALDO_INSUFF = -2;
	


	
	public int controllaOperazione(String operazione, int idconto, float quantita) {
		if(quantita < 0) {
			return QTA_MINORE_0;
		}
		ContoCorrente contoControllo = ccDAO.getContoCorrente(idconto);
		
		if(contoControllo.getSaldo() < quantita) {
			return SALDO_INSUFF;
		}
		return TUTTO_OK;
	}
			
	public boolean preleva(int idconto, float quantita) {
		return ccDAO.prelevaCash(idconto, quantita);
	}
	
	public boolean deposita(int idconto, float quantita) {
		return ccDAO.depositaCash(idconto, quantita);
	}
	
	
	public float saldo(int idconto) {
		return ccDAO.getContoCorrente(idconto).getSaldo();
	}
	

	public boolean esisteCc(int idconto) {
		return ccDAO.getContoCorrente(idconto) != null;
	}
	
	
	public ContoCorrenteDAO getCcDAO() {
		return ccDAO;
	}

	public void setCcDAO(ContoCorrenteDAO ccDAO) {
		this.ccDAO = ccDAO;
	}
}