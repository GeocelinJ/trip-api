package com.sirius.trip.tripapi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sirius.trip.tripapi.model.TouristLocations;
import com.sirius.trip.tripapi.repository.TripApiRepository;
import com.sirius.trip.tripapi.utils.TripApiUtils;

@RestController()
@RequestMapping("/trip/api/")
public class TripApiController {

	@Autowired
	private TripApiRepository tripApiRepository;

	/**
	 * Add the new Tourist Location
	 * 
	 * @param paramMap
	 * @return
	 */
	@PostMapping("touristLocation")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<TouristLocations> createTouristLocations(@RequestBody Map<String, Object> paramMap) {

		try {
			TouristLocations entity = new TouristLocations(TripApiUtils.toString(paramMap.get("location")),
					TripApiUtils.toString(paramMap.get("state")), TripApiUtils.toInteger(paramMap.get("popularity")));

			TouristLocations _entity = tripApiRepository.save(entity);

			return new ResponseEntity<>(_entity, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Get the single Tourist Location based on location id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("touristLocation/{id}")
	public ResponseEntity<TouristLocations> getTouristLocationById(@PathVariable("id") String location) {

		try {
			Optional<TouristLocations> data = tripApiRepository.findById(location);

			if (data.isPresent()) {
				return new ResponseEntity<>(data.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("touristLocation")
	public ResponseEntity<List<TouristLocations>> getAllTouristLocation() {
		try {
			List<TouristLocations> resultList = new ArrayList<TouristLocations>();

			tripApiRepository.findAll().forEach(resultList::add);

			if (resultList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/touristLocation/{id}")
	public ResponseEntity<TouristLocations> updateTutorial(@PathVariable("id") String locationId,
			@RequestBody TouristLocations input) {
		try {
			Optional<TouristLocations> oldData = tripApiRepository.findById(locationId);

			if (oldData.isPresent()) {
				TouristLocations _entity = oldData.get();
				_entity.setState(input.getState());
				_entity.setPopularity(input.getPopularity());
				return new ResponseEntity<>(tripApiRepository.save(_entity), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("touristLocation/{id}")
	public ResponseEntity<HttpStatus> deleteTouristLocation(@PathVariable("id") String location) {
		try {
			tripApiRepository.deleteById(location);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/touristLocation")
	public ResponseEntity<HttpStatus> deleteAllTouristLocations() {
		try {
			tripApiRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("nonCovidArea")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<TouristLocations>> nonCovidArea(@RequestParam (required = false,value="stateList") List<String> stateList) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			String url = "https://mocki.io/v1/5f950b68-0cad-4290-9cf8-120f4d77525f";

			ResponseEntity<Map<String, List<Map<String, Object>>>> response = restTemplate.exchange(url, HttpMethod.GET,
					null, new ParameterizedTypeReference<Map<String, List<Map<String, Object>>>>() {
					});

			if (!response.getStatusCode().equals(HttpStatus.OK)) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Map<String, List<Map<String, Object>>> resultMap = response.getBody();

			List<TouristLocations> finalList = resultMap.keySet().stream().filter(predicate -> {
				if (stateList == null) {
					return true;
				}
				return stateList.contains(predicate);
			}).map(mapper -> {

				List<TouristLocations> rowList = resultMap.get(mapper).stream()
						.map(row -> new TouristLocations(TripApiUtils.toString(row.get("location")), mapper,
								TripApiUtils.toInteger(row.get("popularity"))))
						.collect(Collectors.toList());
				return rowList;
			}).flatMap(mapper -> mapper.stream())
					// sorted based on popularity index
					.sorted(Comparator.comparing(TouristLocations::getPopularity, Collections.reverseOrder()))
					.collect(Collectors.toList());
			return new ResponseEntity<>(finalList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
