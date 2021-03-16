package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.SpringRegistry;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ComptabiliteManagerImplTest {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @BeforeClass
    public static void initContext() {
        SpringRegistry.init();
    }

    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(1234)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3_equilibre() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2020/00008");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null, new BigDecimal(123)
        ));
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }

    @Test
    public void checkEcritureComptableUnitRG5_error() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2021/00008");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null, new BigDecimal(123)
        ));
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }


    @Test
    public void checkAddReference() throws NotFoundException, FunctionalException, ParseException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        journal.setCode("BQ");
        journal.setLibelle("Banque");
        ecritureComptable.setJournal(journal);
        manager.addReference(ecritureComptable);
        int lastValue = manager.getLastSequenceofJournal(journal, 2021).getDerniereValeur();
        Assert.assertEquals(ecritureComptable.getReference(), String.format("%.2s-%4d/%05d", "BQ", 2021, lastValue));
    }

    @Test
    public void testCheckAddReference_with_unexisting_journal() throws FunctionalException, NotFoundException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setDerniereValeur(2);
        sequence.setAnnee(2016);
        journal.setCode("AA");
        journal.setLibelle("Achats");
        ecritureComptable.setJournal(journal);
        manager.addReference(ecritureComptable);
        Assert.assertEquals(ecritureComptable.getReference(), "");

    }
    @Test
    public void testCheckAddReference_with_existing_journal() throws FunctionalException, NotFoundException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setDerniereValeur(2);
        sequence.setAnnee(2021);
        journal.setCode("AC");
        journal.setLibelle("Achats ");
        ecritureComptable.setJournal(journal);
        manager.addReference(ecritureComptable);
        int lastValue = manager.getLastSequenceofJournal(journal, 2021).getDerniereValeur();
        Assert.assertEquals(ecritureComptable.getReference(), String.format("%.2s-%4d/%05d", "AC", 2021, lastValue));
    }

    @Test
    public void testCheckAddReference_with_old_value() throws NotFoundException, FunctionalException, ParseException {

        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        journal.setCode("BQ");
        journal.setLibelle("Banque");
        ecritureComptable.setJournal(journal);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date1 = "01/01/2016";
        ecritureComptable.setDate(simpleDateFormat.parse(date1));
        manager.addReference(ecritureComptable);
        int lastValue = manager.getLastSequenceofJournal(journal, 2016).getDerniereValeur();
        Assert.assertEquals(ecritureComptable.getReference(), String.format("%.2s-%4d/%05d", "BQ", 2016, lastValue));
    }

    @Test(expected = FunctionalException.class)
    public void testCheckAddReference_with_false_year() throws NotFoundException, FunctionalException, ParseException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        journal.setCode("BQ");
        journal.setLibelle("Banque");
        ecritureComptable.setJournal(journal);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date1 = "01/01/20160";
        ecritureComptable.setDate(simpleDateFormat.parse(date1));
        manager.addReference(ecritureComptable);
        int lastValue = manager.getLastSequenceofJournal(journal, 2016).getDerniereValeur();
        Assert.assertEquals(ecritureComptable.getReference(), String.format("%.2s-%4d/%05d", "BQ", 2016, lastValue));
    }

    @Test(expected = FunctionalException.class)
    public void checkCheckEcritureComptable_when_error() throws FunctionalException, NotFoundException, ParseException {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("BQ", "Banque"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date1 = "01/01/2016";
        vEcritureComptable.setDate(simpleDateFormat.parse(date1));
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null, new BigDecimal(1234)
        ));

        manager.checkEcritureComptable(vEcritureComptable);

    }

    @Test
    public void checkCheckEcritureComptable() throws FunctionalException, NotFoundException {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null, new BigDecimal(123)
        ));
        manager.checkEcritureComptable(vEcritureComptable);

    }
    @Test(expected = FunctionalException.class)
    public void checkCheckEcritureComptable_with_one_line() throws FunctionalException, NotFoundException {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptable(vEcritureComptable);

    }
    @Test
    public void checkCheckEcritureComptable_insert() throws FunctionalException, NotFoundException {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                null, null, new BigDecimal(123)
        ));

        manager.insertEcritureComptable(vEcritureComptable);
        manager.deleteEcritureComptable(vEcritureComptable.getId());


    }
    @Test
    public void checkCheckEcritureComptable_update() throws FunctionalException, NotFoundException {
        EcritureComptable vEcritureComptable;
        EcritureComptable vEcritureComptableUpdate;
        vEcritureComptable = new EcritureComptable();

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        manager.addReference(vEcritureComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4457),
                null, null, new BigDecimal(123)
        ));
        manager.insertEcritureComptable(vEcritureComptable);
        vEcritureComptableUpdate=manager.getEcritureComptableByRef(vEcritureComptable.getReference());
        vEcritureComptableUpdate.setLibelle("updated libelle");
        manager.updateEcritureComptable(vEcritureComptableUpdate);

    }

   @Test
    public void test_toString (){
       CompteComptable compteComptable =new CompteComptable();
       EcritureComptable ecritureComptable = new EcritureComptable();
       JournalComptable journalComptable = new JournalComptable();
       LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
       SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();

       System.out.println(compteComptable.toString()
               + ecritureComptable.toString()
               + journalComptable.toString()
               + ligneEcritureComptable.toString()
               + sequenceEcritureComptable.toString());
   }

   @Test(expected = NotFoundException.class)
    public void test_get_by() throws NotFoundException {
       CompteComptable compteComptable =new CompteComptable();
       EcritureComptable ecritureComptable = new EcritureComptable();
       JournalComptable journalComptable = new JournalComptable();
       LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
       SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
       List<JournalComptable> journalList =manager.getListJournalComptable();
       journalComptable = JournalComptable.getByCode(journalList,"AC");
       List<CompteComptable> compteComptableList =manager.getListCompteComptable();
       compteComptable =CompteComptable.getByNumero(compteComptableList,401);
       List<EcritureComptable> ecritureComptableList =manager.getListEcritureComptable();
       ecritureComptable = manager.getEcritureComptable(1);
   }
}
