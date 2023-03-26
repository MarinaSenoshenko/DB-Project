package db.api.service;

import db.entities.CourtSurface;
import db.entities.Sponsor;
import db.repository.CourtSurfaceRepository;
import db.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtSurfaceService {
    private final CourtSurfaceRepository courtSurfaceRepository;

    @Autowired
    public CourtSurfaceService(CourtSurfaceRepository courtSurfaceRepository) {
        this.courtSurfaceRepository = courtSurfaceRepository;
    }

    public CourtSurface addCourtSurface(String value) {
        CourtSurface courtSurface = new CourtSurface(value);
        courtSurfaceRepository.save(courtSurface);
        return courtSurface;
    }
}