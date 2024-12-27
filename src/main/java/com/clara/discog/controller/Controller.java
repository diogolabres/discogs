package com.clara.discog.controller;

import com.clara.discog.dto.ArtistDto;
import com.clara.discog.dto.ComparisonRequestDto;
import com.clara.discog.dto.ComparisonResponseDto;
import com.clara.discog.service.ArtistServiceImpl;
import com.clara.discog.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ArtistServiceImpl artistService;

    @Autowired
    private ReleaseService releaseService;

    @GetMapping("/artists/search-artist")
    public ResponseEntity<List<ArtistDto>> searchArtist(@RequestParam String name) {
        List<ArtistDto> artists = artistService.searchArtistByName(name);
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/artists/comparison")
    public ResponseEntity<ComparisonResponseDto> comparisonArtists(@RequestBody ComparisonRequestDto request) {
        return ResponseEntity.ok(artistService.getComparisonArtist(request));
    }

    @PostMapping("/release/generate")
    public ResponseEntity<String> searchArtist(@RequestBody ArtistDto artist) throws Exception {
        String result = releaseService.saveDiscography(artist);
        return ResponseEntity.ok(result);
    }
}
