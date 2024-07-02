import { Injectable } from '@nestjs/common';
import * as oracledb from 'oracledb';
import { from, Observable } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

@Injectable()
export class OracleDatabaseService {
  private dbConfig = {
    user: 'your_username',
    password: 'your_password',
    connectString: 'localhost:1521/your_service_name'
  };

  getConnection(): Observable<oracledb.Connection> {
    return from(oracledb.getConnection(this.dbConfig)).pipe(
      catchError((error) => {
        console.error('Error connecting to Oracle Database:', error);
        throw error;
      })
    );
  }

  executeQuery(query: string): Observable<oracledb.Result<unknown>> {
    return this.getConnection().pipe(
      switchMap((connection) =>
        from(connection.execute(query)).pipe(
          map((result) => {
            connection.close().catch((err) => {
              console.error('Error closing the connection:', err);
            });
            return result;
          }),
          catchError((error) => {
            console.error('Error executing query:', error);
            connection.close().catch((err) => {
              console.error('Error closing the connection:', err);
            });
            throw error;
          })
        )
      )
    );
  }
}