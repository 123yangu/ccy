package com.zpf.util;

import java.util.List;

public class Page<T> {

	protected Integer pageNo = 1;
	protected Integer pageSize = -1;
	protected Boolean autoCount = Boolean.TRUE;
	protected List<String> orderList = CollectionUtil.newArrayList();
	protected List<String> ascOrderList = CollectionUtil.newArrayList();
	protected List<String> descOrderList = CollectionUtil.newArrayList();

	protected List<T> result = CollectionUtil.newArrayList();

	protected Long totalCount = -1L;

	public String nav = new String();

	public Page() {

	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Page<T> setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Page<T> setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public Boolean getAutoCount() {
		return autoCount;
	}

	public Page<T> setAutoCount(Boolean autoCount) {
		this.autoCount = autoCount;
		return this;
	}

	public List<T> getResult() {
		return result;
	}

	public Page<T> setResult(List<T> result) {
		this.result = result;
		return this;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public Page<T> setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	public Long getTotalPages() {
		if (totalCount < 0 || pageSize == 0) {
			return -1L;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public Boolean getHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	public Integer getNextPage() {
		if (getHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	public Boolean getHasPre() {
		return (pageNo - 1 >= 1);
	}

	public Integer getPrePage() {
		if (getHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public List<String> getOrderList() {
		return orderList;
	}

	public List<String> getAscOrderList() {
		return ascOrderList;
	}

	public List<String> getDescOrderList() {
		return descOrderList;
	}

	public Page<T> asc(String column) {
		orderList.add(column + " ASC");
		ascOrderList.add(column);
		return this;
	}

	public Page<T> desc(String column) {
		orderList.add(column + " DESC");
		descOrderList.add(column);
		return this;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String show(String url) {

		if (totalCount < pageSize) {
			return "";
		}
		StringBuffer navBuf = new StringBuffer();

		navBuf = navBuf
				.append("<div id=\"pagination\" class=\"pagination\"  >");

		if (!url.contains("?")) {
			url += "?1=1";
		}
		if (totalCount <= 1) {
			return navBuf.toString();
		}
		if (pageNo <= 1) {
			navBuf.append("<a class=\"current prev\"  >上一页</a>");
		} else {
			navBuf.append("<a class=\"prev\" href=\"" + url + "&pageIndex="
					+ getPrePage() + "\" > 上一页</a>");
		}

		if (getTotalPages() < 10) {
			for (int i = 1; i <= getTotalPages(); i++) {
				if (i == pageNo) {
					navBuf.append("<span class=\"current\">" + pageNo
							+ "</span>");
				} else {
					navBuf.append("<a href=\"" + url + "&pageIndex=" + (i)
							+ "\">" + (i) + "</a>");
				}
			}
		} else {

			if (pageNo > 6) {
				navBuf.append("<a href=\"" + url + "&pageIndex=1\">1</a>");
				navBuf.append("<a href=\"" + url + "&pageIndex=2\">2</a>");

				navBuf.append("<span class=\"page-break\">...</span>");

				if (pageNo + 5 < getTotalPages()) {
					for (int i = pageNo - 3; i < pageNo + 3; i++) {
						if (i == pageNo) {
							navBuf.append("<span class=\"current\">" + pageNo
									+ "</span>");
						} else {
							navBuf.append("<a href=\"" + url + "&pageIndex="
									+ i + "\">" + i + "</a>");
						}
					}

					if (getTotalPages() > 11 && pageNo + 6 < getTotalPages()) {
						navBuf.append("<span class=\"page-break\">...</span>");
					}

					if (pageNo + 3 < getTotalPages()) {
						int p1 = (int) (getTotalPages() - 1);
						int p2 = (int) (getTotalPages() - 0);
						navBuf.append("<a href=\"" + url + "&pageIndex=" + p1
								+ "\">" + p1 + "</a>");
						navBuf.append("<a href=\"" + url + "&pageIndex=" + p2
								+ "\">" + p2 + "</a>");
					}

				} else {
					for (int i = (int) (getTotalPages() - 7); i <= getTotalPages(); i++) {
						if (i == pageNo) {
							navBuf.append("<span class=\"current\">" + pageNo
									+ "</span>");
						} else {
							navBuf.append("<a href=\"" + url + "&pageIndex="
									+ i + "\">" + i + "</a>");
						}
					}
				}

			} else {
				for (int i = 1; i <= pageNo + 4 && (i + 3 < getTotalPages()); i++) {
					if (i == pageNo) {
						navBuf.append("<span class=\"current\">" + pageNo
								+ "</span>");
					} else {
						navBuf.append("<a href=\"" + url + "&pageIndex=" + (i)
								+ "\">" + (i) + "</a>");
					}
				}

				if (getTotalPages() > 11 && pageNo + 6 < getTotalPages()) {
					navBuf.append("<span class=\"page-break\">...</span>");
				}

				if (pageNo + 3 < getTotalPages()) {
					int p1 = (int) (getTotalPages() - 1);
					int p2 = (int) (getTotalPages() - 0);
					navBuf.append("<a href=\"" + url + "&pageIndex=" + p1
							+ "\">" + p1 + "</a>");
					navBuf.append("<a href=\"" + url + "&pageIndex=" + p2
							+ "\">" + p2 + "</a>");
				}

			}

		}

		if (getHasNext()) {
			navBuf.append("<a href=\"" + url + "&pageIndex=" + getNextPage()
					+ "\" class=\"next\">下一页</a>");
		} else {
			navBuf.append("<a class=\"current next\" >下一页</a>");
		}
		navBuf = navBuf.append("</div>");

		nav = navBuf.toString();
		return nav.toString();

	}

}