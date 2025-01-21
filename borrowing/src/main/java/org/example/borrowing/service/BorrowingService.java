package org.example.borrowing.service;

import org.example.borrowing.dto.BorrowingDTO;
import org.example.borrowing.entity.Borrowing;

import java.util.List;

public interface BorrowingService {

    List<Borrowing> getAllBorrowings();

    Borrowing findById(Long id);

    Borrowing save(Borrowing borrowing);

    void deleteById(Long id);

}
