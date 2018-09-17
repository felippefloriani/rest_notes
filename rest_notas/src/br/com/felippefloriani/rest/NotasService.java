package br.com.felippefloriani.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import br.com.felippefloriani.dao.NotasDAO;
import br.com.felippefloriani.entidade.Nota;;
 
@Path("/notas")
public class NotasService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
    private NotasDAO notasDAO;
    
    //substitui a construção de um construtor pela implementação do próprio Jersey
    @PostConstruct
    private void init() {
        notasDAO = new NotasDAO();
    }
     
    /*
     * Método HTTP de busca que este serviço aceita
     */    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    public List<Nota> listarNotas() {
        List<Nota> lista = null;
        try {
            lista = notasDAO.listarNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
     
     
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    @Produces(MediaType.TEXT_PLAIN)
    public String addNota(Nota nota) {
        String msg = "";
 
        System.out.println(nota.getTitulo());
 
        try {
            int idGerado = notasDAO.addNota(nota);
 
            msg = String.valueOf(idGerado);
        } catch (Exception e) {
            msg = "Erro ao add a nota!";
            e.printStackTrace();
        }
 
        return msg;
    }
     
    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    public Nota buscarPorId(@PathParam("id") int idNota) {
        Nota nota = null;
        try {
            nota = notasDAO.buscarNotaPorId(idNota);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return nota;
    }
 
    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarNota(Nota nota, @PathParam("id") int idNota) {
        String msg = "";
         
        System.out.println(nota.getTitulo());
         
        try {
            notasDAO.editarNota(nota, idNota);
             
            msg = "Nota editada com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao editar a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
     
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String removerNota(@PathParam("id") int idNota) {
        String msg = "";
         
        try {
            notasDAO.removerNota(idNota);
             
            msg = "Nota removida com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao remover a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
	
	

}
