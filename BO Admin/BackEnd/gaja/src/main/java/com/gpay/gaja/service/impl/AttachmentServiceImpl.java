package com.gpay.gaja.service.impl;

import com.gpay.gaja.model.domain.Company;
import com.gpay.gaja.model.domain.Attachment;
import com.gpay.gaja.model.repository.CompanyRepository;
import com.gpay.gaja.model.repository.AttachmentRepository;
import com.gpay.gaja.service.AttachmentService;
import com.gpay.gaja.util.ImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository imageRepo;

    @Autowired
    private CompanyRepository repository;

    // @Autowired
    // private ImageMapper mapper;

    public Attachment uploadImage(MultipartFile file, Long companyId) throws IOException {
        Optional<Company> merchantsOptional = repository.findById(companyId);

        Attachment image = new Attachment();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        image.setCompanyId(merchantsOptional.get());

        return imageRepo.save(image);
    }

    public byte[] downloadImage(String fileName) {
        Optional<Attachment> imageData = imageRepo.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

    // @Override
    // public List<ImageDTO> GetAll() {
    // Iterable<Image> imageIterable = imageRepo.findAll();
    // List<Image> imageList = new ArrayList<>();

    // imageIterable.forEach(imageList::add);
    // List<ImageDTO> imageDTOS = new ArrayList<>();

    // for (Image image : imageList) {
    // ImageDTO images = mapper.convertToDto(image);

    // Merchants merchantsz = new Merchants();
    // merchantsz.setId(image.getMerchantsId().getId());
    // merchantsz.setEmail(image.getMerchantsId().getEmail());
    // merchantsz.setKontakPemBis(image.getMerchantsId().getKontakPemBis());
    // merchantsz.setNamaPemBis(image.getMerchantsId().getNamaPemBis());
    // merchantsz.setKontakPemBis(image.getMerchantsId().getKontakPemBis());
    // merchantsz.setBrand(image.getMerchantsId().getBrand());
    // merchantsz.setEmailBis(image.getMerchantsId().getEmailBis());
    // merchantsz.setKota(image.getMerchantsId().getKota());
    // merchantsz.setKodePos(image.getMerchantsId().getKodePos());
    // merchantsz.setKategoriMerch(image.getMerchantsId().getKategoriMerch());
    // merchantsz.setJmlhMerch(image.getMerchantsId().getJmlhMerch());
    // merchantsz.setAlamat(image.getMerchantsId().getAlamat());
    // merchantsz.setFKtp(image.getMerchantsId().getFKtp());
    // merchantsz.setFSelfie(image.getMerchantsId().getFSelfie());
    // merchantsz.setFLogo(image.getMerchantsId().getFLogo());
    // merchantsz.setBank(image.getMerchantsId().getBank());
    // merchantsz.setNamaPemRek(image.getMerchantsId().getNamaPemRek());
    // merchantsz.setNoRek(image.getMerchantsId().getNoRek());

    // images.setMerchantsz(merchantsz);
    // imageDTOS.add(images);
    // }
    // return imageDTOS;
    // }

    @Override
    public byte[] downloadImages(Company id, String fileName) {

        Optional<Attachment> imageData = imageRepo.findByCompanyIdAndName(id, fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

}
