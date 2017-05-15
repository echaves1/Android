package br.com.fiap.notas.entity;

/**
 * Created by logonpf on 24/04/2017.
 */

public class CloudantResponse {

    private Integer totalRows;
    private Integer offset;
    private Row[] rows = null;

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }
}