/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Token;
import cl.pnk.utils.ConnDBAmazon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TitoS
 */
public class TokenDal {

    private ConnDBAmazon connDBAmazon = new ConnDBAmazon();

    public Token getSession(int idCuenta) {
        Token token = new Token();
        try {
            this.connDBAmazon.conectar();
            String sql = "SELECT TOKEN,ID_CUENTA FROM sesion WHERE ID_CUENTA=?;";
            PreparedStatement sq = this.connDBAmazon.getConexion().prepareStatement(sql);
            sq.setInt(1, idCuenta);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                token.setToken(rs.getString(1));
                token.setIdCuenta(rs.getInt(2));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.connDBAmazon.desconectar();
        }
        return token;
    }
}
