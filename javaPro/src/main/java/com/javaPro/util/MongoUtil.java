package com.javaPro.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;

import com.javaPro.constants.Constants;
import com.mongodb.MongoClient;

public class MongoUtil {
	public static Criteria getCriteria() {
		return Criteria.where("Record_Status").ne(Constants.RECORD_STATUS_DELETED);
//		criteria.orOperator(Criteria.where("A").is(10),Criteria.where("B").is(20));
	}

	public static boolean isNotEmptyOrNull(String prop) {
		if (prop != null && !prop.trim().isEmpty())
			return true;
		return false;
	}

	public static <T> Page<T> getData(Criteria cr, Class<T> claas, Map<String, String> hmap, Pageable pageable) {
		try {
			Query qr = new Query(cr);
			ApplicationContext context;
			MongoOperations mongoOps = new MongoTemplate(new MongoClient("localhost", 27017), "java_pro");
//			MongoOperations mongoOps = context.getBean(MongoOperations.class);
			long count = mongoOps.count(qr, claas);
			if (pageable == null)
				pageable = new PageRequest(0, 20);
			Sort sort = pageable.getSort();
			if (sort == null)
				sort = new Sort(Sort.Direction.DESC, "Created_Date");
			qr.skip(pageable.getPageSize() * pageable.getPageNumber()).limit(pageable.getPageSize()).with(sort);

			HashSet<String> fields = getAllFields(hmap.get("Fields"));
			if (fields.size() > 0) {
				for (String field : fields) {
					qr.fields().include(field);
				}
			}

			List<T> list = mongoOps.find(qr, claas);
			return new PageImpl<>(list, pageable, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PageImpl<>(new ArrayList<T>(0), pageable, 0);
	}

	private static HashSet<String> getAllFields(String str) {
		HashSet<String> fields = new HashSet<String>(0);
		if (MongoUtil.isNotEmptyOrNull(str)) {
			return Stream.of(str.trim().split(",")).filter(f -> MongoUtil.isNotEmptyOrNull(f)).map(m -> m.trim())
					.collect(Collectors.toCollection(HashSet::new));
//			String[] strParts = str.split(",");
//			List<String> listParts = Arrays.asList(strParts);
//			return new HashSet<String>(listParts);
		}
		return fields;
	}
	

	public static HttpHeaders getPaginationHeader(Page<?> page, String baseUrl) throws URISyntaxException {
		try {
			baseUrl = URLEncoder.encode(baseUrl, "UTF8");
		} catch (UnsupportedEncodingException e) {
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", "" + page.getTotalElements());
		String link = "";
		if ((page.getNumber() + 1) < page.getTotalPages()) {
			link = "<" + (new URI(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize())).toString()
					+ ">; rel=\"next\",";
		}
		// prev link
		if ((page.getNumber()) > 0) {
			link += "<" + (new URI(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize())).toString()
					+ ">; rel=\"prev\",";
		}
		// last and first link
		int lastPage = 0;
		if (page.getTotalPages() > 0) {
			lastPage = page.getTotalPages() - 1;
		}
		link += "<" + (new URI(baseUrl + "?page=" + lastPage + "&size=" + page.getSize())).toString()
				+ ">; rel=\"last\",";
		link += "<" + (new URI(baseUrl + "?page=" + 0 + "&size=" + page.getSize())).toString() + ">; rel=\"first\"";
		headers.add(HttpHeaders.LINK, link);
		return headers;
	}

	public static <T extends Serializable> Page<T> listToPageable(List<T> list, Pageable pageable) {
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		Page<T> pages = new PageImpl<>(list.subList(start, end), pageable, list.size());
		return pages;
	}
}

