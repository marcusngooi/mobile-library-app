package ikamjotmarcus.comp304.sec402.lab4.group12.model

import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.LibrarianDao

class LibrarianRepository(private val librarianDao: LibrarianDao) {
    suspend fun getLibrarianPassword(librarianId: String): String {
        return librarianDao.getLibrarianPassword(librarianId)
    }
}