package com.Rudrian.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rudrian.Entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

	List<Cliente> findByCpf (String cpf);
}
