package it.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContoCorrenteDAO {
	
	private Connection conn;
	

	public ContoCorrenteDAO() {
		this.conn = ConnectionFactory.getConnection();
	}

	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ContoCorrente getContoCorrente(int idconto) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContoCorrente ccc = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM contocorrente WHERE idconto = ?");
			ps.setInt(1, idconto);

			rs = ps.executeQuery();
			ccc = new ContoCorrente();

			
			if (rs.next()) {
				ccc.setIdconto(rs.getInt("idconto"));
				ccc.setIntestatario(rs.getString("intestatario"));
				ccc.setSaldo(rs.getFloat("saldo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Recupero info non riuscito!");
		}

		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ccc;
	}

	
	
	public boolean prelevaCash(int idconto, float importo) {
		PreparedStatement ps = null;
		
		
		ContoCorrente ccc = this.getContoCorrente(idconto);
		
		float nuovoSaldo = ccc.getSaldo() - importo;
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE idconto = ?;");
			 
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, idconto);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile prelevare dal conto! " +  e.getMessage());
			
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	
	public boolean depositaCash(int idconto, float quantita) {
PreparedStatement ps = null;
		
		
		ContoCorrente ccc = this.getContoCorrente(idconto);
		
		
		float nuovoSaldo = ccc.getSaldo() + quantita;
		
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE idconto = ?;");
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, idconto);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile depositare nel conto! " +  e.getMessage());
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	
	
	
	public boolean movimentaConto(int idconto, float importo) {
		PreparedStatement ps = null;
		
		
		ContoCorrente ccc = this.getContoCorrente(idconto);
		
		
		float nuovoSaldo = ccc.getSaldo() + importo;
		
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE idconto = ?;");
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, idconto);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile movimentare il conto! " +  e.getMessage());
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	public boolean depositaInConto(int idconto, float importo) {
		return movimentaConto(idconto, importo);
	}
	
	public boolean prelevaDaConto(int idconto, float importo) {
		return movimentaConto(idconto,- importo);
	}
	
	

}