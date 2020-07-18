/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.notificaciones;

/**
 *
 * @author TitoS
 */
public class NotificacionCelular {

    public void enviarNotificacion(String titulo, String mensaje) {
        HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {
                    System.out.println("Notificacion enviada");
                }
            }
        });
        //Parametros GET 'token' y 'visita'
        //Token codigo del dispositvo en tabla sesion 
        String token="cccfXwQEca8:APA91bGI6aax06DPGwzBmNy95oD2n75LCVGG4u0UbzszU7xYk_7oaWO0HCk-bqztrzUsYWAyEBX9LHgcuY9L7EntHZE8v1bW5Ny3-cIeP6zuzJ-D4tGAieZ3n7RH0daXcmeLwT0NkLKN";
        //Ejecutar Consulta
        cliente.excecute("http://34.232.87.118/BD_PNK/push_notificaciones.php?token=" + token + "&mensaje=" + mensaje + "&titulo=" + titulo);
    }
}
