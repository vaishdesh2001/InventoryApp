import java.util.NoSuchElementException;

public interface prodADT {
    public boolean put(long barcodeNum, InventorySystem.Item toAdd);
    public InventorySystem.Item get(long barcodeNum) throws NoSuchElementException;
    public int size();
    public boolean containsKey(long barcodeNum);
    public InventorySystem.Item remove(long barcodeNum);
    public void clear();
}
