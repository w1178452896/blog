package com.taylorsfan.blog.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.model.Sort;
import com.taylorsfan.blog.vo.BlogVo;


@Component
public class SolrUtil {
	// ָ��solr�������ĵ�ַ
	private final static String SOLR_URL = "http://39.107.98.254/solr/";
	String core = "blog";
	HttpSolrClient solr = null;

	public SolrUtil() {
		solr = createSolrServer();
	}

	/**
	 * ����SolrServer����
	 * 
	 * �ö�������������ʹ�ã������̰߳�ȫ�� 1��CommonsHttpSolrServer������web������ʹ�õģ�ͨ��http����� 2��
	 * EmbeddedSolrServer����Ƕʽ�ģ�����solr��jar���Ϳ���ʹ���� 3��solr
	 * 4.0֮���������˲��ٶ���������CommonsHttpSolrServer��������ΪHttpSolrClient
	 * 
	 * @return
	 */
	public HttpSolrClient createSolrServer() {
		solr = new HttpSolrClient.Builder(SOLR_URL + core).withConnectionTimeout(10000).withSocketTimeout(60000)
				.build();
		return solr;
	}

	/**
	 * ������������ĵ������£�
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void addDoc(BlogVo blogVo) {
		// ����һƪ�ĵ�
		SolrInputDocument document = new SolrInputDocument();
		// ��doc������ֶ�,�ڿͻ��������ӵ��ֶα����ڷ�������й�����
		document.addField("id", blogVo.getBlog().getId());
		document.addField("createTime", blogVo.getBlog().getCreateTime());
		document.addField("title", blogVo.getBlog().getTitle());
		document.addField("content", blogVo.getBlog().getContent());
		document.addField("sortName", blogVo.getSort().getSortName());
		document.addField("userCount", blogVo.getUserCount());
		document.addField("commentCount", blogVo.getCommentCount());
		try {
			solr.add(document);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		try {
			solr.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����������
	 * @param vo
	 */
	public void addAll(List<BlogVo> vo) {
	
		for (BlogVo blogVo : vo) {
			SolrInputDocument document = new SolrInputDocument();
			// ��doc������ֶ�,�ڿͻ��������ӵ��ֶα����ڷ�������й�����
			document.addField("id", blogVo.getBlog().getId());
			document.addField("createTime", blogVo.getBlog().getCreateTime());
			document.addField("title", blogVo.getBlog().getTitle());
			document.addField("content", blogVo.getBlog().getContent());
			document.addField("sortName", blogVo.getSort().getSortName());
			document.addField("userCount", blogVo.getUserCount());
			document.addField("commentCount", blogVo.getCommentCount());
			try {
				solr.add(document);
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
			}	
		}
		try {
			solr.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * ����id��������ɾ���ĵ�
	 */
	public void deleteDocumentById(String id) {
		// ɾ���ĵ�
		try {
			solr.deleteById(id);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				solr.commit();

			} catch (SolrServerException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * ɾ��id�б�
	 * 
	 * @param id
	 */
	public void deleteDocumentByIdList(List<String> id) {
		// ɾ���ĵ�
		try {
			solr.deleteById(id);
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				solr.commit();

			} catch (IOException | SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ɾ������
	 * 
	 * @param id
	 */
	public void deleteAll() {
		try {
			solr.deleteByQuery("*:*");
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				solr.commit();

			} catch (IOException | SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ѯ�ؼ���
	 * 
	 * @throws Exception
	 */
	public List<BlogVo> querySolr(String keyWords, Map<String, String> map, int pageNo, int rows) {
		List<BlogVo> list = new LinkedList<>();

		SolrQuery query = new SolrQuery();
		// ��������solr��ѯ����
		query.set("q", keyWords);
		// ����df,��query����Ĭ��������
		query.set("df", "keywords");

		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "" + entry.getValue());
			query.addFilterQuery(entry.getKey() + ":" + entry.getValue());
		}

		// ����sort,���÷��ؽ�����������
		query.setSort("createTime", SolrQuery.ORDER.desc);

		// ���÷�ҳ����
		query.setStart((pageNo - 1) * rows);
		query.setRows(rows);// ÿһҳ����ֵ

		// ����hl,���ø���
		query.setHighlight(true);
		// ���ø������ֶ�
		query.addHighlightField("title");
		query.addHighlightField("content");
		// ���ø�������ʽ
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");

		// ��ȡ��ѯ���
		QueryResponse response = null;
		try {
			response = solr.query(query);
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���ֽ����ȡ���õ��ĵ����ϻ���ʵ�����
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

		SolrDocumentList results = response.getResults();
		for (SolrDocument document : results) {
			BlogVo blogVo = new BlogVo();
			Blog blog = new Blog();
			Sort sort = new Sort();

			String id = (String) document.get("id");
			String createTime = (String) document.get("createTime");
			String title = highlighting.get(id).get("title").get(0);
			String content = highlighting.get(id).get("content").get(0);
			String sortName = (String) document.get("sortName");
			int userCount = (int) document.get("userCount");
			int commentCount = (int) document.get("commentCount");

			blog.setId(Integer.parseInt(id));
			blog.setCreateTime(createTime);
			blog.setTitle(title);
			blog.setContent(content);

			sort.setSortName(sortName);

			blogVo.setBlog(blog);
			blogVo.setSort(sort);
			blogVo.setUserCount(userCount);
			blogVo.setCommentCount(commentCount);

			list.add(blogVo);
		}
		return list;
	}

}