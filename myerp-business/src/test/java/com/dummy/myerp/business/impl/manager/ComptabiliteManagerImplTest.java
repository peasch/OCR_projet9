package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.SpringRegistry;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dummy.myerp.technical.exception.FunctionalException;


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
    public void checkEcritureComptableUnitRG5() throws Exception{
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
                null, null,new BigDecimal(123)
        ));
        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2021/00008");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,new BigDecimal(123)
        ));
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }


    @Test
    public void checkReference() throws Exception{
        System.out.println(manager.getListEcritureComptable());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String date = sdf.format(new Date());
        JournalComptable journal = new JournalComptable();
        journal.setCode("BQ");
        String code = "BQ";
        int ref =23;
        System.out.println(date);
        System.out.println(manager.getLastSequenceofJournal(journal,2016));
        System.out.println(String.format("%05d",ref));
        String reference =(code+"-"+date+"/"+String.format("%05d",ref)).trim();
        System.out.println(reference);
        System.out.println(manager.getListEcritureComptable().size());
        System.out.println( String.format("%.2s-%4d/%05d", "BQ" ,22053, 23));

        System.out.println( manager.getLastSequenceofJournal(journal,2021));
    }

    @Test
    public void checkAddReference() throws NotFoundException, FunctionalException, ParseException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        JournalComptable journal = new JournalComptable();
        SequenceEcritureComptable sequence =new SequenceEcritureComptable();
        journal.setCode("BQ");
        journal.setLibelle("Banque");
        ecritureComptable.setJournal(journal);
        manager.addReference(ecritureComptable);
        Assert.assertEquals(ecritureComptable.getReference(),String.format("%.2s-%4d/%05d", "BQ" ,2021,43));


        sequence.setDerniereValeur(2);
        sequence.setAnnee(2016);
        journal.setCode("AA");
        journal.setLibelle("Achats anticipés");
        ecritureComptable.setJournal(journal);
        manager.addReference(ecritureComptable);
        Assert.assertEquals("",ecritureComptable.getReference());

        journal.setCode("BQ");
        journal.setLibelle("Banque");
        ecritureComptable.setJournal(journal);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date1 ="01/01/2016";
        ecritureComptable.setDate(simpleDateFormat.parse(date1));
        manager.addReference(ecritureComptable);
        Assert.assertEquals(ecritureComptable.getReference(),String.format("%.2s-%4d/%05d", "BQ" ,2016,52));

    }
}
