package db.api.service;

import db.entities.CourtSurface;
import db.repository.CourtSurfaceRepository;
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

    public CourtSurface deleteCourtSurface(Long surfaceId) {
        CourtSurface courtSurface = courtSurfaceRepository.findById(surfaceId).orElseThrow();
        courtSurfaceRepository.delete(courtSurface);
        return courtSurface;
    }

    public CourtSurface updateCourtSurface(Long id, String value) {
        CourtSurface courtSurface = courtSurfaceRepository.findById(id).orElseThrow();
        courtSurface.setValue(value);
        courtSurfaceRepository.save(courtSurface);
        return courtSurface;
    }
}