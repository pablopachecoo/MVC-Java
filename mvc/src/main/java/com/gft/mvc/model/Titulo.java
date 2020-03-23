package com.gft.mvc.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;	
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import net.bytebuddy.asm.Advice.This;

@Entity
public class Titulo { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;  // KEY
	
	@NotEmpty(message = "A Descrição nao pode ser vazia")
	@Size(max = 60, message = "A descrição não pode exceder 60 caracteres")
	private String descricao;  // DESCRICAO
	
	@NotNull(message = "A Data não pode ser vazia")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date dataVencimento;   // DATA
	
	@NotNull(message = "Valor nao pode ser vazio")
	@DecimalMin(value = "0.01", message = "O Valor não pode ser menor que 0,01 R$")
	@DecimalMax(value = "10000", message = "O Valor não pode exceder 10000 R$")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal Valor;   // VALOR
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;  // STATUS
	
	// GETTERS & SETTERS 
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return Valor;
	}
	public void setValor(BigDecimal valor) {
		Valor = valor;
	}
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	
}
