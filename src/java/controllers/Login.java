package controllers;

import BLL.JogadorBLL;
import DTO.JogadorDTO;
import DTO.JuizDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String tipoJogador = request.getParameter("tipoJogador");
        String paginaDestino = "";

        if (tipoJogador.equals("Juiz")) {
            JuizDTO dto = new JogadorBLL().loginJuiz(usuario, senha);
            if (dto != null) {
                request.setAttribute("usuario", dto);
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", dto);
                paginaDestino = "/homeJuiz.jsp";
            } else {
                request.setAttribute("mensagem", "usuario não cadastrado");
                request.getRequestDispatcher("/erro.jsp").forward(request, response);
            }

        } else {
            JogadorDTO dto = new JogadorBLL().loginJogador(usuario, senha);
            if (dto != null) {
                request.setAttribute("usuario", dto);
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", dto);
                paginaDestino = "/homeJogador.jsp";
            } else {
                request.setAttribute("mensagem", "usuario não cadastrado");
                request.getRequestDispatcher("/erro.jsp").forward(request, response);
            }

        }

        request.getRequestDispatcher(paginaDestino).forward(request, response);

    }
}
