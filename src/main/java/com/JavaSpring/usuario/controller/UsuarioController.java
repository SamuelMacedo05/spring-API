package com.JavaSpring.usuario.controller;


import com.JavaSpring.usuario.business.UsuarioService;
import com.JavaSpring.usuario.dto.EnderecoDTO;
import com.JavaSpring.usuario.dto.TelefoneDTO;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.infracture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getEmail())
        );

        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String email){
        usuarioService.deletarUsuarioporEmail(email);
         return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> BuscarUsuarioPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }


    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO dto,
                                                       @RequestHeader ("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco (@RequestBody EnderecoDTO enderecoDTO,
                                                          @RequestParam("id") Long id) {

        return ResponseEntity.ok(usuarioService.atulizarEndereco(id, enderecoDTO));


    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone (@RequestBody TelefoneDTO dto,
                                                          @RequestParam ("id") Long id) {

        return ResponseEntity.ok(usuarioService.atulizarTelefone(id,dto));

    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO>cadastrarEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(dto,token));
    }

    @PostMapping ("/telefone")
     public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO dto,
                                                          @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token,dto));
    }
}



