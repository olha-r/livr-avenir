package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.FormatRepository;

@Service
@Transactional(readOnly = true)
public class FormatServiceImpl implements FormatService {

    private final FormatRepository formats;

    public FormatServiceImpl(FormatRepository formats) {
	this.formats = formats;
    }

    @Override
    public Collection<LabelValue> findAll() {
	return formats.findAllProjectedBy();
    }

}
