package com.pismo.corebankingapi.repository;


import com.pismo.corebankingapi.entity.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPageableRepository extends PagingAndSortingRepository<Transaction, Long> {
}
