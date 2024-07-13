package ru.gb.service;

import ru.gb.repository.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.model.Reader;


import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader getReaderById(Long id) {
        return readerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    @Transactional
    public Reader updateReaders(Long id, Reader reader) {
        Reader updateReader = readerRepository.findById(id).orElseThrow(()-> new RuntimeException("Reader not found"));
        updateReader.setName(reader.getName());
        return readerRepository.save(updateReader);
    }



}
