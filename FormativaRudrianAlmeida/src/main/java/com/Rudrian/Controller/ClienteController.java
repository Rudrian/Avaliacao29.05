package com.Rudrian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rudrian.Entity.Cliente;
import com.Rudrian.Service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Modulo Clientes - ", description = "API REST - GERENCIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;
	
	@Autowired
	public ClienteController (ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(summary = "Busca cliente por CPF")
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Cliente>> buscarClientePorCpf(@PathVariable String cpf){
	List<Cliente> cliente = clienteService.buscarClientePorCpf(cpf);
	return ResponseEntity.ok(cliente);
	}
	
	@Operation(summary = "Busca cliente por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaClienteControlId(@PathVariable Long id){
		Cliente cliente = clienteService.buscaClienteById(id);
		if(cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Busca todos cliente")
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> buscaTodosClienteControl(){
		List<Cliente> clientes = clienteService.buscaTodosCliente();
		return ResponseEntity.ok(clientes);
	}
	
	@Operation(summary = "Adiciona clientes")
	@PostMapping("/")
	public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente){
		Cliente criarCliente = clienteService.salvarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}
	
	@Operation(summary = "Altera cliente por id")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		Cliente alterarCliente = clienteService.alterarCliente(id, cliente);
		if(alterarCliente != null) {
			return ResponseEntity.ok(alterarCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Deleta cliente por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> excluirCliente (@PathVariable Long id){
		boolean excluir = clienteService.excluirCliente(id);
		if(excluir) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
