package com.example.uebungsprojekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface CompanyRepositoy extends JpaRepository<Company, UID> {
}
