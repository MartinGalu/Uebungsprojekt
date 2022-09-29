package com.example.uebungsprojekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepositoy extends JpaRepository<Company, UUID> {
}
