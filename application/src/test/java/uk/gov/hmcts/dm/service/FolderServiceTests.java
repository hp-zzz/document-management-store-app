package uk.gov.hmcts.dm.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.gov.hmcts.dm.componenttests.TestUtil;
import uk.gov.hmcts.dm.domain.Folder;
import uk.gov.hmcts.dm.domain.StoredDocument;
import uk.gov.hmcts.dm.repository.FolderRepository;

@RunWith(MockitoJUnitRunner.class)
public class FolderServiceTests {

    @Mock
    FolderRepository folderRepository;

    @InjectMocks
    FolderService folderService;

    @Test
    public void testFindOne() {

        when(this.folderRepository.findById(TestUtil.RANDOM_UUID)).thenReturn(Optional.ofNullable(TestUtil.folder));

        Folder folder = folderService.findOne(TestUtil.RANDOM_UUID);

        Assert.assertEquals(TestUtil.folder, folder);

    }

    @Test
    public void testSave() {

        Folder folder = new Folder();

        folderService.save(folder);

        verify(folderRepository, times(1)).save(folder);

    }


    @Test
    public void testFindOneItem() {

        when(this.folderRepository.findById(TestUtil.RANDOM_UUID)).thenReturn(Optional.ofNullable(TestUtil.folder));

        StoredDocument storedDocument = folderService.findOneItem(TestUtil.RANDOM_UUID,0);

        Assert.assertEquals(TestUtil.folder.getStoredDocuments().get(0), storedDocument);
    }

    @Test
    public void testFindOneItemFolderNull() {

        when(this.folderRepository.findById(TestUtil.RANDOM_UUID)).thenReturn(Optional.ofNullable(null));

        StoredDocument storedDocument = folderService.findOneItem(TestUtil.RANDOM_UUID,0);

        Assert.assertEquals(null, storedDocument);
    }

    @Test
    public void testDelete() {

        when(this.folderRepository.findById(TestUtil.RANDOM_UUID)).thenReturn(Optional.ofNullable(TestUtil.folder));

        folderService.delete(TestUtil.RANDOM_UUID);

        verify(folderRepository, times(1)).delete(TestUtil.folder);

    }

}
