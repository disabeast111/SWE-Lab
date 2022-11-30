package random;

import exceptions.RecoveryRateException;

public interface Random<A> { A choose() throws RecoveryRateException; }