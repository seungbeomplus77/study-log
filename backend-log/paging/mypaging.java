package com.hs.util;

// 페이지 수 계산
public class MyUtil {
    public int pageCount(int dataCount, int size) {
        if (dataCount <= 0) {
            return 0;
        }
        return dataCount / size + (dataCount % size > 0 ? 1 : 0);
    }

    public String paging(int currentPage, int totalPage, String listUrl) {
        if (currentPage < 1 || currentPage > totalPage) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int pagesPerBlock = 10; // 한 블록당 페이지 수
        int blockStartPage = calculateBlockStartPage(currentPage, pagesPerBlock); // 현재 블록 시작 페이지
        int prevBlockPage = calculatePrevBlockPage(blockStartPage, pagesPerBlock); // 이전 블록 첫 페이지
        int nextBlockPage = calculateNextBlockPage(blockStartPage, pagesPerBlock, totalPage); // 다음 블록 첫 페이지

        listUrl = formatUrl(listUrl);

        sb.append("<div class='paginate'>");

        // 처음과 이전 블록 링크 추가
        if (blockStartPage > 1) {
            sb.append(createLink(listUrl, 1, "처음"));
            sb.append(createLink(listUrl, prevBlockPage, "이전"));
        }

        // 현재 블록의 페이지 번호들 추가
        for (int page = blockStartPage; page < blockStartPage + pagesPerBlock && page <= totalPage; page++) {
            if (page == currentPage) {
                sb.append("<span>").append(page).append("</span>");
            } else {
                sb.append(createLink(listUrl, page, String.valueOf(page)));
            }
        }

        // 다음 블록과 마지막 링크 추가
        if (blockStartPage + pagesPerBlock <= totalPage) {
            sb.append(createLink(listUrl, nextBlockPage, "다음"));
            sb.append(createLink(listUrl, totalPage, "끝"));
        }

        sb.append("</div>");

        return sb.toString();
    }

    // 현재 블록의 첫 페이지 계산
    private int calculateBlockStartPage(int currentPage, int pagesPerBlock) {
        return ((currentPage - 1) / pagesPerBlock) * pagesPerBlock + 1;
    }

    // 이전 블록의 마지막 페이지 계산
    private int calculatePrevBlockPage(int blockStartPage, int pagesPerBlock) {
        return blockStartPage - pagesPerBlock;
    }

    // 다음 블록의 첫 페이지 계산
    private int calculateNextBlockPage(int blockStartPage, int pagesPerBlock, int totalPage) {
        int nextBlockStart = blockStartPage + pagesPerBlock;
        return Math.min(nextBlockStart, totalPage);
    }

    // URL에 ? 또는 & 추가
    private String formatUrl(String url) {
        return url.contains("?") ? url + "&" : url + "?";
    }

    // 링크 생성
    private String createLink(String url, int page, String label) {
        return "<a href='" + url + "page=" + page + "'>" + label + "</a>";
    }
}
