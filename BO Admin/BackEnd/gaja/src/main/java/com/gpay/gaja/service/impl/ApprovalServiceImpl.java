package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.ApprovalDTO;
import com.gpay.gaja.common.mapper.ApprovalMapper;
import com.gpay.gaja.model.domain.Approval;
import com.gpay.gaja.model.repository.ApprovalRepository;
import com.gpay.gaja.service.ApprovalService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalRepository repository;

    @Autowired
    private ApprovalMapper mapper;

    @Override
    public ApprovalDTO save(ApprovalDTO approvalDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(approvalDTO)));
    }

    @Override
    public List<ApprovalDTO> getAll() {
        Iterable<Approval> dto = repository.findAll();
        Iterator<Approval> hmm = dto.iterator();
        List<Approval> yu = new ArrayList<>();
        while (hmm.hasNext()) {
            yu.add(hmm.next());
        }
        List<ApprovalDTO> test = mapper.convertToDto(dto);
        // for (int index = 0; index < test.size(); index++) {
        // test.get(index).setCompanyz(yu.get(index).getCompanyId());
        // }
        return test;
    }

}
