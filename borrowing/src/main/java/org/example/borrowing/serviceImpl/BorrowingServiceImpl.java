package org.example.borrowing.serviceImpl;

import org.example.borrowing.entity.Borrowing;
import org.example.borrowing.repository.BorrowingRepository;
import org.example.borrowing.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Override
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing findById(Long id) {
        return borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
    }

    @Override
    public Borrowing save(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public void deleteById(Long id) {
        borrowingRepository.deleteById(id);
    }
}
